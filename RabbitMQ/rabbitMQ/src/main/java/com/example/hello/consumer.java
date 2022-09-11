package com.example.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 默认持久化 非独占
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello", declare = "false", autoDelete = "false"))
public class consumer {
    
    @RabbitHandler
    public void receive(String message) {
        System.out.println("message = " + message);
    }
}
