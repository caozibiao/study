package com.java.study.jvm.gc;

/**
 * -XX:+PrintGCDetails -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
 */
public class ListTest {
    public static void main(String[] args) {
        ListTest listTest = new ListTest();
        for (int i = 0; i < 10000; i++) {
            listTest.printStr();
        }
    }
    public int printStr(){
        return p(1,3)+p(2,4);
    }
    public int p(int i,int s){
        return s+i;
    }
}
