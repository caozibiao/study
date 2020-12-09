package com.java.study.concurrent.thread;

public class ThreadYieldJoinTest {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("yield test. current thread is " + Thread.currentThread());
                long start = System.currentTimeMillis();
                int sum = 0;
                for(int i=0; i< 10000; i++) {
                    //Thread.yield();
                    sum += i;
                }
                long stop = System.currentTimeMillis();
                System.out.println(stop - start);
                System.out.println("sum: " + sum);
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("等待thread执行后再执行");

    }
}
