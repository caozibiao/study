package com.java.study.concurrent.frame;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    //第二个参数代表是否公平锁
    private static Semaphore semaphore = new Semaphore(10, true);

    public static void main(String[] args) throws Exception{
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        for(int i=0; i<5; i++){
            new Thread(){
                @Override
                public void run(){
                    try{
                        test();
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }.start();
            System.out.println();
        }

    }

    private static void test() throws Exception{
        //获取许可数
        semaphore.acquire(3);
        /**
         * semaphore.availablePermits()： 还能够再获得的许可数
         * semaphore.isFair()： 是否公平锁
         * semaphore.drainPermits(): 将许可数清零
         * semaphore.release(3):可动态释放许可数
         */
        System.out.println("semaphore availablePermits: " + semaphore.availablePermits()
                + ", isFair: " + semaphore.isFair());
        semaphore.release(3);
        System.out.println("semaphore drainPermits: " + semaphore.drainPermits() +
                ", semaphore availablePermits: " + semaphore.availablePermits());

        // 还有多少在等待队列中
        System.out.println("semaphore getQueueLength： " + semaphore.getQueueLength());

    }
}
