drop procedure if exists give;

create procedure give(IN employee_id int, IN give_money_date DATE)
begin
    # 计算次数 --- 多次为结算退房
    DECLARE computed_times int;
    # 计算总结期间 -下一个房间
    DECLARE computed_times_of_next int;
    # 循环中循环的记录id
    DECLARE current_record_id int;

    # 总价格、计算换房的次数
    set @total_price = 0.00;
    set @total_days = 0;
    set computed_times = 0;

    set @change_room_times =
            (SELECT count(*) as times
             from record
             WHERE e_id = employee_id
               and reality_date is null
               and enter_date <= give_money_date);
    #     select @change_room_times as "入住到退房之间 换了几次房";

    while computed_times < @change_room_times
        do

            set current_record_id =
                    (SELECT rr.id from record rr WHERE e_id = employee_id order by rr.id limit computed_times , 1);

            # 所在当前计算次数 的房间号
            set @room_id = (SELECT r_id from record rm WHERE rm.id = current_record_id);
            # select @room_id;

            # 所在房间单价
            set @price_of_day = (select price_of_day from room r1 where r1.id = @room_id);
            # select @price_of_day;

            # 按照上面的房间号 查入住时间
            set @enter_date = (select r.enter_date from record r where r.id = current_record_id);
            # select @enter_date as "入住时间" ,end_date as "收租时间";

            # 获取下一条记录的入住时间  作为本次记录的结束时间
            set computed_times_of_next = computed_times + 1;

            if (@change_room_times <= computed_times_of_next) then
                set @next_record_date = give_money_date;
            else
                set @next_record_date =
                        (SELECT enter_date
                         from record
                         WHERE e_id = employee_id
                         order by id
                         limit computed_times_of_next,1);
            end if;

            # 本次入住总天数
            set @total_day_of_current_time = (select datediff(@next_record_date, @enter_date));
            set @total_days = @total_days + @total_day_of_current_time;

            # 一次的居住时间低于15天 直接按天计价
            if @total_day_of_current_time < 15 then
                set @total_price = @total_price + @price_of_day * @total_day_of_current_time;
            else
                # 轮询计价
                set @enter_date_bak = @enter_date;
                ##
                while @enter_date_bak < @next_record_date
                    do
                        set @year_of_current = year(@enter_date_bak);
                        set @month_of_current = month(@enter_date_bak);
                        set @last = last_day(DATE(concat(@year_of_current, "-", @month_of_current, "-", 01)));
                        # 未结算的居住人数
                        set @hasEmployee = (select count(*)
                                            from record r2
                                            where r2.r_id = @room_id
                                              and r2.reality_date is null
                                              # 入住时间小于当月最后一天
                                              and r2.enter_date < @last);

                        # 总价 = 总计 + (天价*当月天数/当月所在人数)
                        set @total_price = @total_price + (@price_of_day * day(@last) / @hasEmployee);
                        #
#                         select @year_of_current                            as "年",
#                                @month_of_current                           as "月",
#                                @hasEmployee                                as "人数",
#                                (@price_of_day * day(@last) / @hasEmployee) as "消费";

                        set @enter_date_bak = adddate(@enter_date_bak, interval 1 month);
                    end while;
            end if;
            #
#             select @total_day_of_current_time                   as "住了多少天",
#                    @price_of_day                                as "房费",
#                    (@total_day_of_current_time * @price_of_day) as "费用",
#                    @total_price                                 as "本次消费";
            set computed_times = computed_times + 1;
        end while;
#     select @total_days as "总天数", @total_price as "总消费", @total_price / @total_days as "日均费用";
    select @total_days as "总天数", @total_price as "总消费", @total_price / @total_days as "日均费用";
end;

call give(9, NOW())
