package com.java.study.spring.bean;

/**
 * @Author zibiao cao
 * @date 2020/11/17 14:15
 * @Version 1.0
 */

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("这是InstantiationAwareBeanPostProcessor 实现类构造器！！");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass,
                                                 String beanName) throws BeansException {

        if(beanName.equals("person")) {
            System.out.println("InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法");
        }
        return null;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName)
            throws BeansException {
        if(beanName.equals("person")) {
            System.out.println("InstantiationAwareBeanPostProcessor调用postProcessAfterInstantiation方法");
        }
        return true;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
                                                    PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        if(beanName.equals("person")) {
            System.out.println("InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法");
        }

        return pvs;
    }
}
