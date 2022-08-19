drop procedure giveMoney;
DELIMITER $$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE giveMoney(IN id int, In end_date DATETIME, OUT total_price decimal)
BEGIN
    # 所在房间号
    set @roomId = (SELECT r_id from record WHERE e_id = id);
    # select @roomId as "所在房间号";

    # 入住时间
    set @enter_date = (select enter_date from record r where r.id = id and reality_date is null);
    # select @enter_date as "入住时间" ,end_date as "收租时间";

    # 所在房间单价
    set @price_of_day = (select price_of_day from room r3 where r3.id = @roomId);
    # 入住总天数
    set @total_day = (select datediff(end_date, @enter_date));

    # 总价格
    set @total_price = 0;

    if @total_day >= 15 then
        #  两日期之差 - 首月入住天数 - 末月入住天数 = 中间整月天数
        set @first_day = (select DAY(@enter_date));
        set @finally_day = (select DAY(end_date));
        # select @first_day as "入住首月天数" ,@finally_day as "结算当月天数";

        set @total_whole_day = (select datediff(end_date, @enter_date) - @first_day - @finally_day);
        # select @total_whole_day as "入住的整月总天数";

        # 所在房间当前居住人数
        set @hasEmployee = (select count(*) from record r2 where r_id = @roomId and reality_date is null);
        # select @hasEmployee as "所在房间当前居住人数";

        set @enter_date_back = @enter_date;

        while @enter_date_back < end_date
            do
                set @year_of = year(@enter_date_back);
                set @month_of = month(@enter_date_back);

                # 构建 当前月最后一天的日期
                set @currentDate = last_day(DATE(concat(@year_of, "-", @month_of, "-", 01)));
                # 如果首月入住天数小于半月
                if (datediff(@currentDate, @enter_date) < 15) then
                    set @total_price = @total_price + datediff(@currentDate, @enter_date) *
                                                      (@price_of_day / day(@currentDate));
                    # select concat(@year_of,'-',@month_of) as "当前年月份",@total_price as "首月";
                else
                    # 这个月有多少人
                    set @hasEmployeeCurrent =
                            (select count(*)
                             from record r4
                             where r4.r_id = @roomId
                               # 本月入住的人
                               and r4.enter_date < @currentDate
                               and day(r4.enter_date) >= 15);
                    # select DAYOFYEAR(last_day(DATE(concat(2022,"-",02,"-",01))));;
                end if;
                # 如果末月所住天数小于半月
                if (ABS(datediff(end_date, @currentDate)) < 15) then
                    set @total_price =
                                @total_price + datediff(end_date, @currentDate) * (@price_of_day / day(@currentDate));
                end if;
                # select @hasEmployeeCurrent as "当月有多少人";
                # select  concat(@year_of,'-',@month_of) as "当前年月份",(day(@currentDate) * @price_of_day / @hasEmployeeCurrent) as "本月消费";
                set @total_price = @total_price + (day(@currentDate) * @price_of_day / @hasEmployeeCurrent);

                # 加一个月
                set @enter_date_back = date_add(@enter_date_back, interval 1 MONTH);
            end while;
    else
        set @year_of = year(@enter_date);
        set @month_of = month(@enter_date);
        # 构建 当前月最后一天的日期
        set @currentDate = last_day(DATE(concat(@year_of, "-", @month_of, "-", 01)));
        # 平摊价格
        set total_price = @total_day * (@price_of_day / day(@currentDate));
    end if;
    select @total_price as "需缴纳的金额";
END $$
DELIMITER ;

call giveMoney(1, NOW(), @total_price)
