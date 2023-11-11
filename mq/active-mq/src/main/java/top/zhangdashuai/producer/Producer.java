package top.zhangdashuai.producer;

import jakarta.annotation.Resource;
import jakarta.jms.Queue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhangdashuai
 */
@Component
public class Producer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;


    public void producer(String msg) {
        jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}
