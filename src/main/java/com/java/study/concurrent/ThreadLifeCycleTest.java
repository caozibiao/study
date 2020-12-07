package com.java.study.concurrent;

/**
 * 新建 NEW： new Thread()
 * 就绪 RUNNABLE：new Thread().start()
 * 运行 RUNNING： 当线程获得cpu，开始执行run方法的线程执行体
 * 阻塞 BLOCKED：
 *     等待阻塞 WAIT：JVM把线程放入等待队列中（waiting queue）
 *     同步阻塞 LOCK：若同步锁被别的线程占有，JVM会把线程放入锁池中（lock pool）
 *     其他阻塞 SLEEP/JOIN/IO阻塞：
 * 死亡
 *
 *
 */
public class ThreadLifeCycleTest {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().toString() + System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().toString() + System.currentTimeMillis());
        }).start();

        Thread thread1 = new Thread(() -> {
            while (true) {
                System.out.println("thread1 " + System.currentTimeMillis());
            }

        });
        thread1.start();
        try {
            thread1.wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

