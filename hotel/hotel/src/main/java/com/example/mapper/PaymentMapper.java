package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dto.PaymentSearchDTO;
import com.example.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 17602
 * @description 针对表【payment】的数据库操作Mapper
 * @createDate 2022-08-19 10:42:22
 * @Entity com.example.entity.Payment
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
    IPage<Payment> pay(IPage<?> page, PaymentSearchDTO paymentSearchDTO);
}
