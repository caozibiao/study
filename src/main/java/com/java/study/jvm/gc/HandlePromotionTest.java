package com.java.study.jvm.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class HandlePromotionTest {
    public static void main(String[] args) {
        byte [] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * 1024 * 1024];
        allocation2 = new byte[2 * 1024 * 1024];
        allocation3 = new byte[2 * 1024 * 1024];
        allocation1 = null;
        allocation4 = new byte[2 * 1024 * 1024];
        allocation5 = new byte[2 * 1024 * 1024];
        allocation6 = new byte[2 * 1024 * 1024];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * 1024 * 1024];
    }
}
