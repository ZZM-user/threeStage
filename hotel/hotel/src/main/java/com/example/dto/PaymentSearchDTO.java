package com.example.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @Author： 17602
 * @Date： 2022/8/20 21:46
 * @Desc：
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSearchDTO extends PageDTO {
    
    /**
     * 结账人id
     */
    Integer id;
    
    /**
     * 结账年
     */
    Integer endYear;
    
    /**
     * 结账月
     */
    Integer endMonth;
    /**
     * 总价
     */
    BigDecimal totalPrice;
}
