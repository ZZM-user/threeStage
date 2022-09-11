package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.RabbitMQUtil;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Topic 动态路由模式
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
        channel.exchangeDeclare("test_topic", "topic");
        String[] routingKeys = new String[]{"message.info", "notice.pic.info", "message.warring", "notice.pic.warring", "message.danger", "notice.pic.danger"};
        
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Random random = new Random();
            String routingKey = routingKeys[random.nextInt(routingKeys.length)];
            String message = routingKey + "\t" + scanner.nextLine();
            channel.basicPublish("test_topic", routingKey, null, message.getBytes());
            System.out.println(routingKey + "\t" + "消息已发送！");
        }
        
    }
}
