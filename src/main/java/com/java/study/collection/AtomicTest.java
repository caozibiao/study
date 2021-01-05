package com.java.study.collection;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author zibiao cao
 * @date 2020/12/16 9:33
 * @Version 1.0
 */
public class AtomicTest {
    //必须volatile
    private volatile int id;
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        int i = atomicInteger.addAndGet(2);
        boolean b = atomicInteger.compareAndSet(0, 13);
        System.out.println(atomicInteger.intValue());

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
        atomicIntegerArray.addAndGet(0, 6);
        atomicIntegerArray.compareAndSet(2, 3, 5);
        System.out.println(atomicIntegerArray.get(2));

        AtomicIntegerFieldUpdater<AtomicTest> atomicIntegerFieldUpdater =  AtomicIntegerFieldUpdater.newUpdater(AtomicTest.class, "id");
        AtomicTest atomicTest = new AtomicTest();
        atomicIntegerFieldUpdater.compareAndSet(atomicTest, 0, 8);
        System.out.println(atomicTest.id);
    }
}
