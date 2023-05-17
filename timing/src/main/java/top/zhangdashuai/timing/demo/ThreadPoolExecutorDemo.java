package top.zhangdashuai.timing.demo;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangdashuai
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "zhangdashuai-thread"), new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.execute(() -> System.out.println("定时任务执中:" + new Date()));
        threadPoolExecutor.shutdown();
    }
}
