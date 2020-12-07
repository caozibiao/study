package com.java.study.concurrent;

import java.util.concurrent.*;

public class ThreadTest {
    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                System.out.println("extends Thread: " + Thread.currentThread());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("implements Runnable: " + Thread.currentThread().getThreadGroup());
            }
        }).start();

        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future future = pool.submit(new Callable<Object>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().toString();
            }
        });
        System.out.println(pool.toString());

        try {
            System.out.println(future.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        while(true){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
