package top.zhangdashuai.timing.demo.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @author zhangdashuai
 */
@DisallowConcurrentExecution
public class ZhangDaShuaiJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("定时任务执中:" + new Date());
    }
}
