package com.java.study.spring.bean;

/**
 * @Author zibiao cao
 * @date 2020/11/17 14:16
 * @Version 1.0
 */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    int i = 1;

    public MyBeanFactoryPostProcessor() {
        super();
        System.out.println("这是BeanFactoryPostProcessor实现类构造器！！");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {

        if(beanFactory.getBeanDefinition("person") != null && i == 1) {
            i--;
            System.out.println("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
        }

        BeanDefinition bd = beanFactory.getBeanDefinition("person");
        bd.getPropertyValues().addPropertyValue("phone", "110");
    }

}
