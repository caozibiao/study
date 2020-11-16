package com.java.study.spring.proxy;

/**
 * @Author zibiao cao
 * @date 2020/11/16 9:19
 * @Version 1.0
 */
public class StaticProxy {
    private Subject subject;
    public StaticProxy(){}

    public StaticProxy(Subject subject){
        this.subject = subject;
    }

    public void request(String param) {
        System.out.println("-------proxy----------");
        subject.request(param);
    }
}
