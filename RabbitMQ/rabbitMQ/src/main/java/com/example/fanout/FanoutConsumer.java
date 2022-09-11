package com.example.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author： 17602
 * @Date： 2022/9/11 20:47
 * @Desc：
 **/
@Component
public class FanoutConsumer {
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(value = "test logs", type = "fanout")
            )
    })
    public void receive01(String message) {
        System.out.println("01 = " + message);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(value = "test logs", type = "fanout")
            )
    })
    public void receive02(String message) {
        System.out.println("02 = " + message);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(value = "test logs", type = "fanout")
            )
    })
    public void receive03(String message) {
        System.out.println("03 = " + message);
    }
    
}
