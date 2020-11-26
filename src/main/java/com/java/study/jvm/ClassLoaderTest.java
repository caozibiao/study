package com.java.study.jvm;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        FirstClassLoader firstClassLoader = new FirstClassLoader("/Users/caozibiao/Desktop/project/test");
        Class clazz = firstClassLoader.loadClass("com.java.study.StudyApplication");
        System.out.println(clazz.getClassLoader());
    }
}
