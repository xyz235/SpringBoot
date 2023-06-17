package top.zhangdashuai.projectstartautomaticexecutionmethod.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhangdashuai
 */
@Component
@Order(1)
public class CommandLineRunnerDemo implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("CommandLineRunnerDemo");
    }
}
