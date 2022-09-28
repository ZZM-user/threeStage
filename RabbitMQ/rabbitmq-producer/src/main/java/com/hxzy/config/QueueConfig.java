package com.hxzy.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 队列配置
 */
@Configuration
public class QueueConfig {
    
    @Bean
    public Queue hello() {
        return new Queue("hello_boot");
    }
}
