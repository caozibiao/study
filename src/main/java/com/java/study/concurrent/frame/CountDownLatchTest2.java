package com.java.study.concurrent.frame;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest2 {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        for(int i =0; i<10; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " ready");
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " end");
                }
            }.start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all ready, go next");
        countDownLatch.countDown();

    }
}
