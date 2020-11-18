package com.java.study.spring.ioc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author zibiao cao
 * @date 2020/11/13 16:10
 * @Version 1.0
 */
//@Component
public class FactoryBeanDemo implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        System.out.println("----get dog--------");
        return new Dog();
    }

    @Override
    public Class<?> getObjectType() {
        System.out.println("----get dog type--------");
        return Dog.class;
    }
}
