package com.java.study.concurrent.frame;

import java.util.concurrent.Phaser;

/**
 * @Author zibiao cao
 * @date 2020/12/10 10:26
 * @Version 1.0
 */
public class PhaserArriveTest {
    private static Phaser phaser = new Phaser(3) {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println("-------arrived, now advance-----");
            return super.onAdvance(phase, registeredParties);
        }
    };
    public static void main(String[] args) {
        for(int i =0; i<3; i++){
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +" ready");
                    // 使parties增加1，并不在屏障处到达
                    phaser.arrive();
                    //phaser.arriveAndAwaitAdvance();
                    // phaser.arrive() 使parties增加1，并不在屏障处到达，直接执行下面语句
                    System.out.println(Thread.currentThread().getName() +" end");
                }
            }.start();
        }
    }
}
