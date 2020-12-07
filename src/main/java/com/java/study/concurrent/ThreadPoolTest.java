package com.java.study.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService pool1 = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 3; i++) {
            final int m = i;
            pool1.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().toString() + m);
                }
            });
        }

        ExecutorService pool2 = Executors.newCachedThreadPool();
        for(int i = 0; i < 3; i++) {
            final int m = i;
            pool2.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().toString() + m);
                }
            });
        }

        ExecutorService pool3 = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 3; i++) {
            final int m = i;
            pool3.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().toString() + m);
                }
            });
        }

        ScheduledExecutorService pool4 = Executors.newScheduledThreadPool(2);
        for(int i = 0; i < 3; i++) {
            final int m = i;
            pool4.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().toString() + m);
                }
            }, 1,3, TimeUnit.SECONDS);
        }
    }
}
