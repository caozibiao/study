package com.java.study.spring.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author zibiao cao
 * @date 2020/11/16 9:32
 * @Version 1.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        StaticProxy staticProxy = new StaticProxy(subject);
        staticProxy.request("------static proxy-------");

        JdkDynamicProxy jdkDynamicProxy = new JdkDynamicProxy(subject);
        Subject jdkProxy = (Subject)Proxy.newProxyInstance(RealSubject.class.getClassLoader(), RealSubject.class.getInterfaces(), jdkDynamicProxy);
        jdkProxy.request("-----jdk proxy---------");

        CglibDynamicProxy cglibDynamicProxy = new CglibDynamicProxy();
        RealSubject cglibProxy = cglibDynamicProxy.getProxy(RealSubject.class);
        cglibProxy.request("----cglib class proxy");


    }
}
