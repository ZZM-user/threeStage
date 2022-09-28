package com.example.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 17602
 * @Date： 2022/9/26 15:04
 * @Desc：
 **/
@RequestMapping("/")
@RestController
public class SendMessage {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @RequestMapping("/hello")
    public void hello() {
        rabbitTemplate.convertAndSend("/hello");
    }
    
    @RequestMapping("/")
    public void send() {
        rabbitTemplate.convertAndSend("/");
    }
}
