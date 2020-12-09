package com.java.study.concurrent.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTest {
    private List<String> list = new ArrayList<>();
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        for(int m=0; m<3;m++) {
            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        threadLocalTest.list.add(Thread.currentThread() + ":" + i);
                        threadLocalTest.threadLocal.set(Thread.currentThread() + ":" + i);
                        System.out.println(threadLocalTest.threadLocal.get());
                        System.out.println(threadLocalTest.list.get(i));
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        for(int i=0;i<100;i++){
            threadLocalTest.list.add(Thread.currentThread() + ":" + i);
            threadLocalTest.threadLocal.set(Thread.currentThread() + ":" + i);
            //System.out.println(threadLocalTest.threadLocal.get());
            System.out.println(threadLocalTest.list.get(i));
        }


    }
}
