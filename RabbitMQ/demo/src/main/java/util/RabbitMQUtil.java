package util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 兔子队列工具类
 *
 * @Author： 17602
 * @Date： 2022/9/11 13:18
 * @Desc： 兔子队列工具类
 **/
public class RabbitMQUtil {
    
    /**
     * 获取兔子连接
     *
     * @return connection
     */
    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        
        // 工厂的ip 连接RabbitMQ队列
        factory.setHost("47.96.162.196");
        factory.setUsername("admin");
        factory.setPassword("admin");
        
        try {
            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
