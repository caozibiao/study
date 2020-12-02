package com.java.study.jvm.string;

/**
 * @Author zibiao cao
 * @date 2020/11/26 14:54
 * @Version 1.0
 */
public class JdkTest {
    public static void main(String[] args) {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }
        };
    }
}
