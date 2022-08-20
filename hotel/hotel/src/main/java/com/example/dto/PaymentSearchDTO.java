package com.example.dto;

import java.util.List;

/**
 * @Author： 17602
 * @Date： 2022/8/20 21:46
 * @Desc：
 **/
public class PaymentSearchDTO extends PageDTO {
    
    /**
     * 结账人id
     */
    List<Integer> ids;
    
    /**
     * 结账年
     */
    Integer year;
    
    /**
     * 结账月
     */
    Integer month;
}
