package com.java.study.jvm.string;

public class ImmutableTest {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        s2 += "def";
        System.out.println(s1);
        System.out.println(s2);
    }
}
