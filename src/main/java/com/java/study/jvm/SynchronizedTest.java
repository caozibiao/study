package com.java.study.jvm;

public class SynchronizedTest {
    // 实例方法，方法访问标志ACC_SYNCHRONIZED,锁对象是对象实例
    public synchronized void test1(){}
    // 静态方法，方法访问标志ACC_SYNCHRONIZED,锁对象是MetaSpace中的Class
    // 相当于类的全局锁，会锁住所有调用该方法的线程
    public synchronized static void test2(){}

    public void test3() {
        //同步代码块，在代码块前增加monitorenter指令，代码块后增加monitorexit指令
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        synchronized (synchronizedTest) {}
        // 类锁，效果等同于锁静态方法。代码块前后增加monitorenter、monitorexit指令
        synchronized (SynchronizedTest.class) {}
    }
}
