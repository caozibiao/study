package com.java.study.spring.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author zibiao cao
 * @date 2020/11/16 9:24
 * @Version 1.0
 */
public class CglibDynamicProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T)enhancer.create();
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy){
        try {
            return methodProxy.invokeSuper(object, args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
