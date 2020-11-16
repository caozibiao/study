package com.java.study.spring.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author zibiao cao
 * @date 2020/11/13 9:30
 * @Version 1.0
 */
@Aspect
@Component
public class AopLog {
    @Pointcut("execution (* com.java.study.spring.controller.*.*(..))")
    public void logPointcut() {
        System.out.println("-------------logPointcut---------");
    }

    @Before("logPointcut()")
    void before() {
        System.out.println("-------------before----------");
    }

    @After("logPointcut()")
    void after() {
        System.out.println("-------------after----------");
    }

    @AfterReturning("logPointcut()")
    void afterReturning() {
        System.out.println("-------------afterReturning----------");
    }

    @AfterThrowing("logPointcut()")
    void afterThrowing() {
        System.out.println("-------------afterThrowing----------");
    }

    /*@Around("logPointcut()")
    void around() {
        System.out.println("-------------around----------");
    }*/
}
