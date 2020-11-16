package com.java.study.spring.service.impl;

import com.java.study.spring.ioc.FactoryBeanDemo;
import com.java.study.spring.service.SpringService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Author zibiao cao
 * @date 2020/11/13 16:40
 * @Version 1.0
 */
@Service
public class SpringServiceImpl implements SpringService {
    @Override
    public String factoryBeanTest() throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanDemo.class);
        FactoryBeanDemo bean = (FactoryBeanDemo)context.getBean("factoryBeanDemo");
        System.out.println("-----------" + bean);
        FactoryBeanDemo bean1 = (FactoryBeanDemo)context.getBean("&factoryBeanDemo");
        return bean1.getObject().toString();
    }
}
