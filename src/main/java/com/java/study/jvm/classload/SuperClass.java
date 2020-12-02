package com.java.study.jvm.classload;

/**
 * @Author zibiao cao
 * @date 2020/11/30 10:37
 * @Version 1.0
 */
class SuperClass{

    static {
        value = 1;
        System.out.println("SuperClass init" );
    }
    public static int value = 123;
}
