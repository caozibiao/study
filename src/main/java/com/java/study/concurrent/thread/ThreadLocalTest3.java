package com.java.study.concurrent.thread;

public class ThreadLocalTest3 {
    public static void main(String[] args) {
        new Thread("test") {
            @Override
            public void run() {
                ThreadLocal threadLocal = new ThreadLocal();
                threadLocal.set(1);
            }
        }.start();
    }
}
