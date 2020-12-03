package com.java.study.jvm.error;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author zibiao cao
 * @date 2020/12/2 17:01
 * @Version 1.0
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        Set<String> set =  new HashSet<>();
        short i = 0;
        while(true) {
            set.add(String.valueOf(i).intern());
        }
    }
}
