package top.zhangdashuai.timing.demo.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author zhangdashuai
 */
public class QuartzDemo {
    public static void main(String[] args) throws SchedulerException {
        /*创建Scheduler工厂*/
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        /*从工厂中获取调度器实例*/
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        /*注册任务和定时器*/
        scheduler.scheduleJob(JobBuilder.newJob(ZhangDaShuaiJob.class).build(), TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?")).build());
        /*启动调度器*/
        scheduler.start();
    }
}
