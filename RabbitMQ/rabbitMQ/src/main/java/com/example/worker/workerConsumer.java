package com.example.worker;


import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author： 17602
 * @Date： 2022/9/11 20:34
 * @Desc：
 **/
@Component
public class workerConsumer {
    
    @RabbitListener(queuesToDeclare = @Queue("worker"))
    public void consumer01(String message) {
        System.out.println("01 = " + message);
    }
    
    @RabbitListener(queuesToDeclare = @Queue("worker"))
    public void consumer02(String message) {
        System.out.println("02 = " + message);
    }
    
    @RabbitListener(queuesToDeclare = @Queue("worker"))
    public void consumer03(String message) {
        System.out.println("03 = " + message);
    }
}
