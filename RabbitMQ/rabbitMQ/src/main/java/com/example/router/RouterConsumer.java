package com.example.router;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author： 17602
 * @Date： 2022/9/11 21:16
 * @Desc：
 **/
@Component
public class RouterConsumer {
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(name = "test_directs", type = "direct"),
                    key = {"application.info"}
            )
    })
    public void receive01(String message) {
        System.out.println("01 = " + message);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(name = "test_directs", type = "direct"),
                    key = {"application.debug"}
            )
    })
    public void receive02(String message) {
        System.out.println("02 = " + message);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(),
                    exchange = @Exchange(name = "test_directs", type = "direct"),
                    key = {"application.error"}
            )
    })
    public void receive03(String message) {
        System.out.println("03 = " + message);
    }
}
