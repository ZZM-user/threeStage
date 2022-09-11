package com.example.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author： 17602
 * @Date： 2022/9/11 21:29
 * @Desc：
 **/
@Component
public class TopicConsumer {
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "test_topics", type = "topic"),
                    key = {"application.#"}
            )
    })
    public void receive01(String topic) {
        System.out.println("topic 01 = " + topic);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "test_topics", type = "topic"),
                    key = {"application.#.info"}
            )
    })
    public void receive02(String topic) {
        System.out.println("topic 02 = " + topic);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "test_topics", type = "topic"),
                    key = {"application.#.debug"}
            )
    })
    public void receive03(String topic) {
        System.out.println("topic 03 = " + topic);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "test_topics", type = "topic"),
                    key = {"application.*.error"}
            )
    })
    public void receive04(String topic) {
        System.out.println("topic 04 = " + topic);
    }
}
