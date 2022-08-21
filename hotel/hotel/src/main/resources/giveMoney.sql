drop procedure if exists giveMoneyOfMonth;
DELIMITER $$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE giveMoneyOfMonth(IN employee_id int, In end_year INT, IN end_month INT,
                                                            OUT totalPrice DECIMAL(10, 2))
BEGIN
    # 总价 = 0
    declare total_price DECIMAL(10, 2) default 0.0;
    # 计算次数 = 0
    declare compute_times INT default 0;
    # loop中的下一次计算
    declare compute_time_next INT default 0;
    declare has_not_pay_room_of_before TINYINT default 0;

    # 构建这个年月的第一天
    set @first_day_of = DATE(concat(end_year, "-", end_month, "-", 01));
    # 构建这个年月的最后一天
    set @last_day_of = last_day(@first_day_of);
    # 计算当前年月换房次数
    set @change_room_times =
            (SELECT count(*) as times
             from record
             WHERE e_id = employee_id
               and enter_date between @first_day_of and @last_day_of);

    loop_flag:
    loop
        if compute_times >= @change_room_times
        then
            leave loop_flag;
        end if;
        # 获取当前员工本次记录id
        set @current_record_id =
                (SELECT rr.id
                 from record rr
                 WHERE e_id = employee_id
                   and enter_date between @first_day_of and @last_day_of
                 order by rr.id
                 limit compute_times , 1);
        # 入住日期 = 当前记录的入住日期
        set @start_date =
                (SELECT enter_date from record WHERE id = @current_record_id);

        # 如果本次入住记录 就在本月 代表之前在其他房间住过
        if @start_date > @first_day_of
        then
            # 获取上一间房间的价格
            set @before_room_price =
                    (select (select rom.price_of_day as price from room rom where rom.id = r0.r_id)
                     from record r0
                     where e_id = employee_id
                       and r0.enter_date < @first_day_of
                     order by r0.id desc
                     limit 1);
            if @before_room_price is not null
            then
                # 加进总价
                set total_price = total_price + (@before_room_price * (DAY(@start_date) - 1));
            end if;
        else
            # 如果 入住天数 < 本月第一天
            set @start_date = @first_day_of;
        end if;
        # 所在当前计算次数 的房间号
        set @room_id = (SELECT r_id from record rm WHERE rm.id = @current_record_id);

        # 所在房间单价
        set @price_of_day = (select price_of_day from room r1 where r1.id = @room_id);

        set compute_time_next = compute_times + 1;
        # # if 这不是最后一次计算
        if compute_time_next < @change_room_times
        then
            # # # 本次住宿结束日期 = 下一次的换房日期
            set @leave_date =
                    (SELECT enter_date from record WHERE e_id = employee_id order by id limit compute_time_next, 1);
        else
            # # # 本次住宿结束日期 = 当前年月最后一天
            set @leave_date = @last_day_of;
        end if;

        set @reality_leave_date =
                (SELECT reality_date from record WHERE e_id = employee_id order by id limit compute_times, 1);
        # # if 有主动退房日期
        if @reality_leave_date is not null
        then
            # # # 本次住宿结束日期 = 主动退房日期
            set @leave_date = @reality_leave_date;
        end if;

        # # 计算本次住宿天数
        set @enter_days = datediff(@leave_date, @start_date);

        set @month_has_day = day(@last_day_of);
        # # if 住宿天数 > 15天
        if @enter_days > @month_has_day div 2
        then
            # # # 获取当前宿舍  住宿天数 大于半月的人数
            # # # # 获取所有成员id
            # # # # while
            # # # # #
            set @has_employee = (select count(*)
                                 from record r2
                                 where r2.r_id = @room_id
                                   # 入住时间小于当月最后一天
                                   and r2.enter_date < @last_day_of);
            # # # 总价 = 总价 + 月天数 * 天价 / 当前宿舍  住宿天数 大于半月的人数
            set total_price = total_price + @enter_days * @price_of_day / @has_employee;
        else
            # # # 总价 = 总价 + 住宿天数 * 天价 / 当前月天数
            set total_price = total_price + @enter_days * @price_of_day;
        end if;
        # # # # 计算次数 = 计算次数 + 1
        set compute_times = compute_times + 1;

    end loop;
    # 打印总价
    set totalPrice = total_price;
    select totalPrice;

END;
DELIMITER ;

call hotel.giveMoneyOfMonth(9, 2021, 7, @total_prices);
