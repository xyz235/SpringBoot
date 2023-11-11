package top.zhangdashuai.config;

import jakarta.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhangdashuai
 */
@Component
public class ActiveMqConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("testQueue");
    }
}
