package com.java.study.concurrent.frame;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {
    private static Exchanger<String> exchanger = new Exchanger<>();
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //exchange方法具有阻塞特性
                    System.out.println("Thread0 get Theard1 name: " + exchanger.exchange(Thread.currentThread().getName(), 5, TimeUnit.SECONDS));
                    System.out.println("after exchange");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    //exchange方法具有阻塞特性
                    System.out.println("Thread1 get Theard0 name: " + exchanger.exchange(Thread.currentThread().getName(), 5, TimeUnit.SECONDS));
                    System.out.println("after exchange");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //前面已经交换完了，这里会报java.util.concurrent.TimeoutException
        new Thread(){
            @Override
            public void run() {
                try {
                    //exchange方法具有阻塞特性
                    System.out.println("Thread2 get Theard0 name: " + exchanger.exchange(Thread.currentThread().getName(), 5, TimeUnit.SECONDS));
                    System.out.println("after exchange");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        System.out.println("exchanger end");
    }
}
