package com.java.study.jvm;

public class SynchronizedTest {
    public synchronized void test(){}

    public void test1() {
        synchronized (SynchronizedTest.class) {
            System.out.println(1);
        }
    }
}
