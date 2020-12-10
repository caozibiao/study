package com.java.study.concurrent.frame;

import java.util.concurrent.Phaser;

/**
 * @Author zibiao cao
 * @date 2020/12/10 9:54
 * @Version 1.0
 */
public class PhaserTest {
    private static Phaser phaser = new Phaser(2){
        // 通过屏障时被调用
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println("--------onAdvance start--------" + ", phase: " + phase + ", registeredParties: " + registeredParties);
            return true;
        }
    };
    public static void main(String[] args) {
        for(int i = 0;i<3; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " ready");
                    /**
                     *  getPhase 获取已经到达的第几个屏障
                     *  getRegisteredParties 注册的屏障数
                     *  getArrivedParties  获取已到达的parties
                     *  getUnarrivedParties 获取未被使用的parties
                     */
                    System.out.println("getPhase: " + phaser.getPhase() + ", getArrivedParties: " + phaser.getArrivedParties()
                            + ", getUnarrivedParties: " + phaser.getUnarrivedParties() +", getRegisteredParties: " + phaser.getRegisteredParties());
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(Thread.currentThread().getName() + " end. " +
                            "getPhase: " + phaser.getPhase() + ", getArrivedParties: " + phaser.getArrivedParties()
                    +", getRegisteredParties: " + phaser.getRegisteredParties());

                    System.out.println(Thread.currentThread().getName() + " ready------");
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(Thread.currentThread().getName() + " end-------");
                }
            }.start();
        }
        System.out.println("phaser register begin");
        // 可动态增加屏障
        phaser.register();
        // 可批量动态增加屏障
        //phaser.bulkRegister(10);
        System.out.println(Thread.currentThread().getName() + " end. " +
                "getPhase: " + phaser.getPhase() + ", getArrivedParties: " + phaser.getArrivedParties()
                +", getRegisteredParties: " + phaser.getRegisteredParties());
    }
}
