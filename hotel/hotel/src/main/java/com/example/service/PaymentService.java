package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.PaymentSearchDTO;
import com.example.entity.Payment;

import java.util.Map;

/**
 * @author 17602
 * @description 针对表【payment】的数据库操作Service
 * @createDate 2022-08-19 10:42:22
 */
public interface PaymentService extends IService<Payment> {
    /**
     * 月结
     *
     * @return
     */
    Map pay(PaymentSearchDTO paymentSearchDTO);
}
