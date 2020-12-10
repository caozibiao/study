package com.java.study.concurrent.frame;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author zibiao cao
 * @date 2020/12/10 9:16
 * @Version 1.0
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Thread(){
            @Override
            public void run() {
                System.out.println("---ready, go next---");
            }
        });

        for(int i=0; i<5;i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " ready, " +
                            "getNumberWaiting " + cyclicBarrier.getNumberWaiting() + ", " +
                            "getParties " + cyclicBarrier.getParties());
                    try {
                        cyclicBarrier.await();
                        System.out.println("2 thread had ready");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            try {
                Thread .sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
