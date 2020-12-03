package com.java.study.jvm.error;

/**
 * @Author zibiao cao
 * @date 2020/12/2 16:02
 * @Version 1.0
 */
public class StackOom {
    private void dontStop() {
        while(true){}
    }

    private void stackLeakByThread() {
        while(true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        StackOom stackOom = new StackOom();
        stackOom.stackLeakByThread();
    }
}
