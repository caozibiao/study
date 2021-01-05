package com.java.study.concurrent.thread;

/**
 * @Author zibiao cao
 * @date 2020/12/18 16:26
 * @Version 1.0
 */
public class ThreadLocalTest2 {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal();
        for(int i = 0; i<3; i++) {
            threadLocal.set(i);
        }
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
    }
}
