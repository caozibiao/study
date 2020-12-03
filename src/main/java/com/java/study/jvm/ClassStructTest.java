package com.java.study.jvm;

public abstract class ClassStructTest extends Father implements ParentInterface{
    private static String str1 = "str1";
    private final String str2 = "str2";
    {
        str1 = "static_str1";
    }

    public String test1(Object obj, int i1, int i2) {
        int i3 = (i1 + i2) * (i1 - i2);
        String objStr = obj.toString();
        return objStr;
    }

    abstract void test2();

    class InnerClass{}


}

interface ParentInterface{}

class Father{}
