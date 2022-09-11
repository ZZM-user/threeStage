package dfanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.RabbitMQUtil;

import java.io.IOException;
import java.util.Scanner;

/**
 * 广播模式
 *
 * @Author： 17602
 * @Date： 2022/9/11 17:24
 * @Desc：生产者
 **/
public class Provider {
    
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        // 将通道声明交换机
        // 1、交换机名称
        // 2、交换机类型 fanout（广播）
        channel.exchangeDeclare("test", "fanout");
        
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.nextLine();
            channel.basicPublish("test", "", null, message.getBytes());
            System.out.println("消息发送成功：" + message);
        }
    }
}
