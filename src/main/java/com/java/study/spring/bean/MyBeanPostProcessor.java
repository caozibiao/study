package com.java.study.spring.bean;

/**
 * @Author zibiao cao
 * @date 2020/11/17 14:14
 * @Version 1.0
 */
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("这是BeanPostProcessor实现类构造器！！");
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if(beanName.equals("person")){
            System.out.println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！");
        }

        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if(beanName.equals("person")) {
            System.out.println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！");
        }
        return bean;
    }
}
