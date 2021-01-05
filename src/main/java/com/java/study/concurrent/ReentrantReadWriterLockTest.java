package com.java.study.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author zibiao cao
 * @date 2020/12/18 9:15
 * @Version 1.0
 */
public class ReentrantReadWriterLockTest {
    private volatile boolean update;
    public void processData() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        readLock.lock();
        if(!update) {
            //必须先释放读锁
            readLock.unlock();
            // 锁降级从写锁获取到开始
            writeLock.lock();
            try{
                if(!update) {
                    update = true;
                }
                // 可以获取到读锁，getExclusiveOwnerThread() == current
                readLock.lock();
            } finally {
                writeLock.unlock();
            }
            //锁降级完成，写锁降级为读锁
        }
        try{
            // 使用数据的流程
        } finally {
            readLock.unlock();
        }
    }
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        System.out.println(lock.getQueueLength() + ", " + lock.getReadHoldCount() + ", " + lock.getReadLockCount());
        for (int i = 0; i < 3; i++) {
            new Thread(Thread.currentThread().getName() + i) {
                @Override
                public void run() {
                    readLock.lock();
                    readLock.lock();
                    System.out.println("lock: " + lock.getQueueLength() + ", " + lock.getReadHoldCount() + ", " + lock.getReadLockCount());
                    readLock.unlock();
                    readLock.lock();
                    System.out.println("unlock: " + lock.getQueueLength() + ", " + lock.getReadHoldCount() + ", " + lock.getReadLockCount());
                    readLock.unlock();
                    writeLock.lock();
                    System.out.println("writeLock: " + writeLock.getHoldCount());
                    writeLock.unlock();
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            System.out.println(lock.getQueueLength() + ", " + lock.getReadHoldCount() + ", " + lock.getReadLockCount());

        }
    }
}
