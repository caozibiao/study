package com.java.study.concurrent.frame;

import java.util.concurrent.*;

/**
 * @Author zibiao cao
 * @date 2020/12/10 10:58
 * @Version 1.0
 */
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(3, 5, 10,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        Future future = pool.submit(new Callable<Object>() {
            @Override
            public String call() {
                System.out.println("----future-----");
                return "Future";
            }
        });
        try {
            System.out.println("future.get : " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
