package com.java.study.concurrent.frame;

import com.java.study.concurrent.thread.ThreadLockTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTest2 {

    private static Semaphore semaphore = new Semaphore(3);
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception{
        for(int i = 0; i<5;i++) {
            new Thread() {
                @Override
                public void run() {
                    //test1();
                    test2();
                }
            }.start();
        }

    }

    /**
     * 多进路——多处理——多出路
     */
    private static void test1() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " prepare");
            for(int i =0; i<5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " print");
            }
            System.out.println(Thread.currentThread().getName() + " for end");
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " end");
            System.out.println(semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多进路——单处理——多出路
     */
    private static void test2() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " prepare");
            lock.lock();
            for(int i =0; i<5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " print");
            }
            System.out.println(Thread.currentThread().getName() + " for end");
            lock.unlock();
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " end");
            System.out.println("semaphore.availablePermits: " + semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
