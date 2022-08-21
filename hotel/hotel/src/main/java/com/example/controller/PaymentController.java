package com.example.controller;

import com.example.common.domain.R;
import com.example.dto.PaymentSearchDTO;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author： 17602
 * @Date： 2022/8/21 20:41
 * @Desc：
 **/
@RestController
@RequestMapping("api/pay")
public class PaymentController {
    @Autowired
    PaymentService service;
    
    @PostMapping("payment")
    public R pay(@RequestBody PaymentSearchDTO paymentSearchDTO) {
        System.out.println(paymentSearchDTO);
        
        BigDecimal totalPrice = BigDecimal.ZERO;
        paymentSearchDTO.setTotalPrice(totalPrice);
        Map pay = service.pay(paymentSearchDTO);
        return R.okHasData(pay);
    }
}
