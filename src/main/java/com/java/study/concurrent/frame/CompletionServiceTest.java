package com.java.study.concurrent.frame;

import java.util.concurrent.*;

/**
 * @Author zibiao cao
 * @date 2020/12/10 11:10
 * @Version 1.0
 */
public class CompletionServiceTest {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, new SynchronousQueue<>(),
                new ThreadPoolExecutor.DiscardPolicy());
        CompletionService completionService = new ExecutorCompletionService(pool);

        for(int i=0; i<5;i++) {
            completionService.submit(new Callable() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + " call");
                    return Thread.currentThread().getName();
                }
            });
        }

        for(int i=0; i<5;i++) {
            try {
                //不阻塞，哪个任务先执行完，就先打印
                System.out.println("take: " + completionService.take());
                System.out.println("poll: " + completionService.poll());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
