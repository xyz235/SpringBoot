package top.zhangdashuai.projectstartautomaticexecutionmethod.java;

import org.springframework.stereotype.Component;

/**
 * @author zhangdahuai
 */
@Component
public class JavaDemo {

    static {
        System.out.println("static");
    }

    public JavaDemo(){
        System.out.println("JavaDemo");
    }
}
