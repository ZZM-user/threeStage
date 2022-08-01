package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.OrderDetail;
import com.example.service.OrderDetailService;
import com.example.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author 17602
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-08-01 14:06:02
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




