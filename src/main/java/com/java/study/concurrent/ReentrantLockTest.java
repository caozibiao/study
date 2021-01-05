package com.java.study.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zibiao cao
 * @date 2020/12/17 15:15
 * @Version 1.0
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        for(int i = 0; i < 3; i++) {
            int m = i;
            new Thread("thread-" + i) {
                @Override
                public void run() {
                    lock.lock();
                    System.out.println("-----------");
                    if(m == 0) {
                        try {
                            Thread.sleep(10000000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if(m == 1) {
                        try {
                            Thread.sleep(100000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    lock.unlock();
                }
            }.start();
        }

    }
}
