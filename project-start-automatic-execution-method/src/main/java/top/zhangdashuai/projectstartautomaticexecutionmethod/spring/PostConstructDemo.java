package top.zhangdashuai.projectstartautomaticexecutionmethod.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author zhangdashuai
 */
@Component
public class PostConstructDemo {

    @PostConstruct
    public void postConstructDemo() {
        System.out.println("PostConstructDemo");
    }
}
