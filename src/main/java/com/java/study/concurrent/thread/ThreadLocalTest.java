package com.java.study.concurrent.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTest {
    private List<String> list = new ArrayList<>();
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public void test() {
        String str = threadLocal.get();
        //do something
        threadLocal.remove();
        threadLocal = null;
    }
    public static void main(String[] args) {
        List list = new ArrayList();
        System.out.println(list.subList(0,3));
        int n = 1;
        n >>>= 1;
        System.out.println(n);
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

                        //System.out.println(Thread.currentThread().);
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
