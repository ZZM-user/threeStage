package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.RabbitMQUtil;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * 路由模式
 *
 * @Author： 17602
 * @Date： 2022/9/11 18:05
 * @Desc： 生产者
 **/
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        // 建立联系
        // 交换机名称；路由模式
        channel.exchangeDeclare("test_direct", "direct");
        String[] routingKeys = new String[]{"info", "warring", "danger"};
        
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Random random = new Random();
            String routingKey = routingKeys[random.nextInt(3)];
            String message = routingKey + "\t" + scanner.nextLine();
            channel.basicPublish("test_direct", routingKey, null, message.getBytes());
            System.out.println(routingKey + "\t" + "消息已发送！");
        }
        
    }
}
