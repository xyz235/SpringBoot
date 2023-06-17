package top.zhangdashuai.projectstartautomaticexecutionmethod.spring;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhangdashuai
 */
@Component
@Order(2)
public class ApplicationRunnerDemo implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("ApplicationRunnerDemo");
    }
}
