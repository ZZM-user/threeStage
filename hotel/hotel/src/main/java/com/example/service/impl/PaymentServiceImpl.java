package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.PaymentSearchDTO;
import com.example.entity.Payment;
import com.example.mapper.PaymentMapper;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 17602
 * @description 针对表【payment】的数据库操作Service实现
 * @createDate 2022-08-19 10:42:22
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
        implements PaymentService {
    @Autowired
    PaymentMapper mapper;
    
    /**
     * 月结
     *
     * @param page
     * @param paymentSearchDTO
     *
     * @return
     */
    @Override
    public Map pay(PaymentSearchDTO paymentSearchDTO) {
        return mapper.pay(paymentSearchDTO);
    }
}
