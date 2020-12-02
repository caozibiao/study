package com.java.study.jvm.init;

/**
 * @Author zibiao cao
 * @date 2020/11/30 15:56
 * @Version 1.0
 */
public class InitTest1 {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}

class SuperClass{
    public static int value = 123;
    static {
        value = 1;
        System.out.println("SuperClass init" );
    }
    //public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}