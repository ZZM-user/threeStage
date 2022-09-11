package aconsumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 *
 * @Author： 17602
 * @Date： 2022/9/10 16:32
 * @Desc：消费者
 **/
public class Consumer {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";
    
    // 接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        
        // 工厂的ip 连接RabbitMQ队列
        factory.setHost("47.96.162.196");
        factory.setUsername("admin");
        factory.setPassword("admin");
        
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        /*
        消费者消费消息
            1、消费哪个队列
            2、消费成功后是否要自动应答
            3、消费者未成功消费时的回调
            4、消费者取消消费时的回调
         */
        DeliverCallback confirmCallback = (consumerTag, message) -> {
            System.out.println("该消息已接收：message = " + new String(message.getBody()));
        };
        CancelCallback blockedCallback = (consumerTag) -> {
            System.out.println("该消息被中断：consumerTag = " + consumerTag);
        };
        channel.basicConsume(QUEUE_NAME, true, confirmCallback, blockedCallback);
        
        
    }
}
