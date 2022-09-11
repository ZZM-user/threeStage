package aconsumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 * @Author： 17602
 * @Date： 2022/9/10 16:07
 * @Desc： 生产者
 **/
public class Producer {
    
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";
    
    // 发消息
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        
        // 工厂的ip 连接RabbitMQ队列
        factory.setHost("47.96.162.196");
        factory.setUsername("admin");
        factory.setPassword("admin");
        
        // 创建链接
        Connection connection = factory.newConnection();
        // 获取信道
        Channel channel = connection.createChannel();
        
        /*
          生成一个队列
             1、队列名称
             2、队列中的消息是否持久化 默认保存在内存中
             3、该队列是否共享消息，是否只供一个消费者消费
             4、是否自动删除，最后一个消费者断连后，是否自动删除队列
             5、其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        String message = "你好啊";
        
        /*
            发送一个信息
                1、发送到哪个交换机
                2、路由的key值是哪个 本次是交换机的名称
                3、其他参数
                3、需要发送的消息体 (二进制)
         */
        while (true) {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息发送完毕");
            Thread.sleep(2000);
        }
        
    }
    
}
