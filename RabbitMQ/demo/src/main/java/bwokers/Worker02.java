package bwokers;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import util.RabbitMQUtil;

import java.io.IOException;

/**
 * 工作线程02
 *
 * @Author： 17602
 * @Date： 2022/9/11 13:27
 * @Desc： 工作线程02
 **/
public class Worker02 {
    
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";
    
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        DeliverCallback confirmCallback = (consumerTag, message) -> {
            System.out.println("该消息02已接收：message = " + new String(message.getBody()));
        };
        CancelCallback blockedCallback = (consumerTag) -> {
            System.out.println("该消息被02中断：consumerTag = " + consumerTag);
        };
        
        channel.basicConsume(QUEUE_NAME, true, confirmCallback, blockedCallback);
    }
}
