package com.java.study.jvm.string;

import com.java.study.jvm.interfaces.InterfaceTest;

import java.io.IOException;

/**
 * @Author zibiao cao
 * @date 2020/11/24 8:39
 * @Version 1.0
 */
public class ClassTest extends SuperClass implements Interface1 {
    String str;

    {
        str = "static";
    }

    private int num = 1;

    public String getString(String str1) throws IOException {
        return str + num + str1;
    }
}

class SuperClass{}

interface Interface1{}
