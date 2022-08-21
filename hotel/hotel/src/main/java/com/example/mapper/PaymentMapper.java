package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.PaymentSearchDTO;
import com.example.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author 17602
 * @description 针对表【payment】的数据库操作Mapper
 * @createDate 2022-08-19 10:42:22
 * @Entity com.example.entity.Payment
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
    /**
     * 月结
     *
     * @param page
     * @param paymentSearchDTO
     *
     * @return
     */
    Map pay(PaymentSearchDTO paymentSearchDTO);
}
