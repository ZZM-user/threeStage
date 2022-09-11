package topic;

import com.rabbitmq.client.*;
import util.RabbitMQUtil;

import java.io.IOException;

/**
 * info
 *
 * @Author： 17602
 * @Date： 2022/9/11 17:36
 * @Desc： 消费者01
 **/
public class Consumer03 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        // 绑定交换机
        channel.exchangeDeclare("test_topic", "topic");
        
        // 临时队列
        String queue = channel.queueDeclare().getQueue();
        
        // *：一个单词
        // #：多个单词
        String routingKey = "#.danger";
        
        // 绑定交换机和对列
        // 1、队列名
        // 2、交换机
        // 3、路由key
        channel.queueBind(queue, "test_topic", routingKey);
        
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("我拿到了：" + new String(body));
            }
        });
    }
}
