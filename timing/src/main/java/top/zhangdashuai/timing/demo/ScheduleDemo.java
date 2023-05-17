package top.zhangdashuai.timing.demo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhangdashuai
 */
@Component
@EnableScheduling
public class ScheduleDemo {
    @Scheduled(cron = "* * * * * ?")
    public void scheduledDemo() {
        System.out.println("定时任务执中:" + new Date());
    }
}
