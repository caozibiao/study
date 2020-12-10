package com.java.study.concurrent.frame;

import java.util.concurrent.*;

/**
 * @Author zibiao cao
 * @date 2020/12/10 13:22
 * @Version 1.0
 */
public class ForkJoinTest {
    private static int start = 1;
    private static int end = 10;
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        test(forkJoinPool,start,end);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ForkJoinTask forkJoinTask = new RecursiveTask<String>() {
            @Override
            protected String compute() {
                return Thread.currentThread().getName();
            }
        };
        System.out.println("forkJoinTask hashcode: " + forkJoinTask.hashCode() + " ,getActiveThreadCount" + forkJoinPool.getActiveThreadCount()
        + ", getPoolSize"+ forkJoinPool.getPoolSize() + ", getParallelism" + forkJoinPool.getParallelism());
        try {
            ForkJoinTask forkJoinTask2 = forkJoinPool.submit(forkJoinTask);
            System.out.println("forkJoinTask2 hashcode: " + forkJoinTask2.hashCode() + ", get:" + forkJoinTask2.get()
            + " ,join: " + forkJoinTask2.join());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void test(ForkJoinPool forkJoinPool, int start, int end) {
        forkJoinPool.submit(new RecursiveAction() {
            @Override
            protected void compute() {
                System.out.println(Thread.currentThread().getName() +" compute");
                if(end - start > 2){
                    int middel = (start + end) / 2;
                    test(forkJoinPool, start, middel);
                    test(forkJoinPool, middel +1, end);
                } else {
                    System.out.println(Thread.currentThread().getName() + " start: " + start +", end: " + end);
                }

            }
        });
    }
}
