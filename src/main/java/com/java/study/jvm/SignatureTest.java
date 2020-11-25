package com.java.study.jvm;

import java.util.ArrayList;
import java.util.List;

public class SignatureTest {
    List<String> list1;

    public void test(List<String> list) {
        list = new ArrayList<>();
    }
}
