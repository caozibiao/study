package com.java.study.concurrent.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 未加lock之前，打印的i会乱序；
 * 加lock后必然执行完一个thread再执行另一个，则i有序
 */
public class ThreadLockTest {
    public static void main(String[] args) {
        ThreadLockTest threadLockTest = new ThreadLockTest();
        for(int i=0; i<10;i++){
            new Thread() {
                @Override
                public void run() {
                    threadLockTest.test();
                }
            }.start();
        }
    }

    private ReentrantLock lock = new ReentrantLock();

    private void test() {
        lock.lock();
        for(int i =0; i<50; i++) {
            System.out.println("current thread " + Thread.currentThread().getName() + " " + i);
        }
        lock.unlock();
    }
}
