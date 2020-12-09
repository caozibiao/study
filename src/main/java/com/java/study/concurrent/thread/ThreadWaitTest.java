package com.java.study.concurrent.thread;

/**
 *  notify()方法执行后并不立即释放锁
 */
public class ThreadWaitTest {
    public static void main(String[] args) {
        ThreadWaitTest lock = new ThreadWaitTest();
        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " begin:" + System.currentTimeMillis());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " end:" + System.currentTimeMillis());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " begin2:" + System.currentTimeMillis());
                    try {
                        lock.notify();
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " end2:" + System.currentTimeMillis());
                }
            }
        }.start();
    }
}
