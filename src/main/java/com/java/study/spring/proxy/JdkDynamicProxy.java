package com.java.study.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author zibiao cao
 * @date 2020/11/16 9:21
 * @Version 1.0
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object target;

    public JdkDynamicProxy(){}

    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
