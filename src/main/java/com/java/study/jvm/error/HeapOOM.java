package com.java.study.jvm.error;

import java.util.ArrayList;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 */
public class HeapOOM {
    static class OOMObject{}

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();
        while(true) {
            list.add(new OOMObject());
        }
    }
}
