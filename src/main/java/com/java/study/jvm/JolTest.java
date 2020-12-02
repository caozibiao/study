package com.java.study.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author zibiao cao
 * @date 2020/12/1 16:44
 * @Version 1.0
 */
public class JolTest {
    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
