package bwokers;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import util.RabbitMQUtil;

import java.io.IOException;

/**
 * 工作线程01
 *
 * @Author： 17602
 * @Date： 2022/9/11 13:27
 * @Desc： 工作线程01
 **/
public class Worker01 {
    
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";
    
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        DeliverCallback confirmCallback = (consumerTag, message) -> {
            System.out.println("该消息01已接收：message = " + new String(message.getBody()));
        };
        CancelCallback blockedCallback = (consumerTag) -> {
            System.out.println("该消息被01中断：consumerTag = " + consumerTag);
        };
        
        channel.basicConsume(QUEUE_NAME, true, confirmCallback, blockedCallback);
    }
}
