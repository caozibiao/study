package com.java.study.jvm;

import java.util.HashSet;
import java.util.Set;

public class RunTimeConstantPoolOom {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        short i = 0;
        while(true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
