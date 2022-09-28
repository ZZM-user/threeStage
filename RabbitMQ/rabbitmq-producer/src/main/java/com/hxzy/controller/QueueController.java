package com.hxzy.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueController {
    
    
    /**
     * 消息队列发送模板类
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    
    @GetMapping(value = "/send/msg")
    public String sendMsg(String message) {
        rabbitTemplate.convertAndSend("hello_boot", message);
        return Thread.currentThread().getId() + ",生产者，发送消息成功";
    }
    
    
    @GetMapping(value = "/send/qian/msg")
    public String sendMsgQian(String message) {
        for (int i = 1; i <= 100000000000L; i++) {
            rabbitTemplate.convertAndSend("hello_boot", message);
        }
        
        return Thread.currentThread().getId() + ",生产者，发送消息成功";
    }
    
    
}
