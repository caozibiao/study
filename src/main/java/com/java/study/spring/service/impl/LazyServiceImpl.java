package com.java.study.spring.service.impl;

import com.java.study.spring.lazy.LazyConfig;
import com.java.study.spring.service.LazyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Author zibiao cao
 * @date 2020/11/13 13:50
 * @Version 1.0
 */
@Service
public class LazyServiceImpl implements LazyService {

    @Autowired
    private LazyConfig lazyConfig;

    @Override
    public String getLazyBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyConfig.class);
        Object lazyConfig = context.getBean("lazyConfig");
        return lazyConfig.toString();
    }

    @Override
    public void lazyDependent() {
        lazyConfig.lazy();
    }
}
