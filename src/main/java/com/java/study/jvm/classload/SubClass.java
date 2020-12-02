package com.java.study.jvm.classload;

/**
 * @Author zibiao cao
 * @date 2020/11/30 10:38
 * @Version 1.0
 */
class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init");
    }
}