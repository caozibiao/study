package com.java.study.jvm.error;

/**
 * -Xss128k -XX:+PrintGCDetails
 */
public class StackSof {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackSof stackSof = new StackSof();
        try{
            stackSof.stackLeak();
        } catch (Throwable e){
            System.out.println("stack length:" + stackSof.stackLength);
            throw e;
        }
    }
}
