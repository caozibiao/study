package com.java.study.concurrent.frame;

import java.util.concurrent.*;

/**
 * @Author zibiao cao
 * @date 2020/12/10 10:45
 * @Version 1.0
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        ExecutorService pool1 = Executors.newFixedThreadPool(3);
        ExecutorService pool2 = Executors.newCachedThreadPool();
        ExecutorService pool3 = Executors.newSingleThreadExecutor();
        ExecutorService pool4 = Executors.newScheduledThreadPool(4);

        ThreadPoolExecutor pool5 = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        pool5.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-----");
            }
        });

        for(int i=0;i<6;i++) {
            pool5.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "-----");
                }
            });
        }

    }
}
