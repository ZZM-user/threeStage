package cmanual;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import util.RabbitMQUtil;

import java.io.IOException;

/**
 * 消费者01
 *
 * @Author： 17602
 * @Date： 2022/9/11 14:06
 * @Desc： 消费者01
 **/
public class Task01 {
    public static String TASK_QUEUE_NAME = "ack manual";
    
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        // 预期值2条，默认0条，即为不公平分发，默认轮询分发
        channel.basicQos(2);
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("消息已被01应答 = " + new String(message.getBody()));
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息已被01丢失 = " + consumerTag);
        };
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, cancelCallback);
    }
}
