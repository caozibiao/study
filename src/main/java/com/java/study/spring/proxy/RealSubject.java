package com.java.study.spring.proxy;

/**
 * @Author zibiao cao
 * @date 2020/11/16 9:18
 * @Version 1.0
 */
 class RealSubject implements Subject{

    @Override
    public void request(String param) {
        System.out.println(param);
    }
}
