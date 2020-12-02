package com.java.study.jvm;

import org.apache.catalina.loader.WebappClassLoader;
import org.apache.catalina.loader.WebappClassLoaderBase;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        /*FirstClassLoader firstClassLoader = new FirstClassLoader("/Users/caozibiao/Desktop/project/test");
        Class clazz = firstClassLoader.loadClass("com.java.study.StudyApplication");
        System.out.println(clazz.getClassLoader());*/
        /*ClassLoader loader = ClassLoaderTest.class.getClassLoader().getParent();
        Class<?> aClass = loader.loadClass("java.sql.DriverManager");
        System.out.println(aClass.getClassLoader());*/
        WebappClassLoader webappClassLoaderBase = new WebappClassLoader();
        webappClassLoaderBase.loadClass("java.sql.DriverManager");
    }
}
