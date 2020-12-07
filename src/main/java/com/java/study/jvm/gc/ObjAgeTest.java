package com.java.study.jvm.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX: MaxTenuringThreshold=1
 */
public class ObjAgeTest {
    public static void main(String[] args) {
        byte [] allocation1, allocation2, allocation3;
        allocation1 = new byte[1 * 1024 * 1024 / 4];
        allocation2 = new byte[4 * 1024 * 1024];
        allocation3 = new byte[4 * 1024 * 1024];
        allocation3 = null;
        allocation3 = new byte[4 * 1024 * 1024];
    }
}
