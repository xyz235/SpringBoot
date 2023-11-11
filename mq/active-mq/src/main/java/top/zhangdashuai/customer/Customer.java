package top.zhangdashuai.customer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangdashuai
 */
@Component
public class Customer {

    @JmsListener(destination = "testQueue")
    public void customer(String msg){
        System.out.print(msg);
    }
}
