package com.java.study.spring.proxy;

import java.lang.reflect.*;

/**
 * @Author zibiao cao
 * @date 2020/11/17 8:59
 * @Version 1.0
 */
public class JdkProxy implements InvocationHandler {
    private Object target;
    public JdkProxy(){}
    public JdkProxy(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
