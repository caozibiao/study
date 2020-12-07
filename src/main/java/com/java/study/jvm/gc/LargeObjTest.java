package com.java.study.jvm.gc;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728对Parallel Scavenge不生效
 */
public class LargeObjTest {
    public static void main(String[] args) {
        byte [] allocation = new byte[5 * 1024 * 1024];
    }
}
