package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17602
* @description 针对表【order_detail(订单明细表)】的数据库操作Mapper
* @createDate 2022-08-01 14:06:02
* @Entity com.example.entity.OrderDetail
*/
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}
