package com.example;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * 众多测试
 */
@SpringBootTest(classes = RabbitMqApplication.class)
@RunWith(SpringRunner.class)
class RabbitMqApplicationTests {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    /**
     * 消费者
     */
    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend("hello", "hello world springboot");
    }
    
    /**
     * 工作模式
     */
    @Test
    void testWorker() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("worker", i + "worker模型");
        }
    }
    
    /**
     * 广播模式
     */
    @Test
    void testFanout() {
        for (int i = 0; i < 3; i++) {
            rabbitTemplate.convertAndSend("test logs", "", "全体起立");
        }
    }
    
    
    /**
     * 订阅路由
     */
    private static final String[] levels = {"application.info", "application.debug", "application.error", "application.dev.info", "application.dev.debug", "application.dev.error"};
    
    String getLevelName() {
        return levels[new Random().nextInt(levels.length)];
    }
    
    /**
     * 路由模式
     */
    @Test
    void testRouter() {
        for (int i = 0; i < 10; i++) {
            String levelName = getLevelName();
            rabbitTemplate.convertAndSend("test_directs", levelName, levelName + " = 全体起立");
        }
    }
    
    /**
     * 动态路由 订阅模式
     */
    @Test
    void testTopic() {
        for (int i = 0; i < 10; i++) {
            String levelName = getLevelName();
            rabbitTemplate.convertAndSend("test_topics", levelName, levelName + " = topic = 全体起立");
        }
    }
    
}
