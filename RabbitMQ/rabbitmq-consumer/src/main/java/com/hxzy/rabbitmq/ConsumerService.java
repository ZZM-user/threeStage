package com.hxzy.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
// 监听消息队列里面的  队列名
@RabbitListener(queues = "hello_boot")
public class ConsumerService {
    
    
    /**
     * 消息费者，消费 Queue=hxzy_hello里面的内容
     *
     * @param message
     */
    @RabbitHandler
    public void consumer(String message) {
        System.out.println(Thread.currentThread().getId() + ",消费者接收内容:");
        System.out.println(message);
        System.out.println();
    }
}
