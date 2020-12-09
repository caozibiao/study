package com.java.study.concurrent.frame;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    private static int length = 10;
    private static CountDownLatch countDownLatch = new CountDownLatch(length);
    public static void main(String[] args) {
        for(int i = 0; i<length;i++) {
            new Thread(){
                @Override
                public void run() {
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + " handle, countDown count: " + countDownLatch.getCount());
                }
            }.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all handled, next");
    }
}
