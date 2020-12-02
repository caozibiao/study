package com.java.study.spring.controller;

import com.java.study.spring.bean.Person;
import com.java.study.spring.service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zibiao cao
 * @date 2020/11/13 16:40
 * @Version 1.0
 */
@RestController
public class SpringController {
    @Autowired
    private SpringService springService;
    @Autowired
    private Environment environment;

    @Autowired
    private AnnotationConfigServletWebServerApplicationContext context;

    @RequestMapping("/factoryBeanTest")
    public String factoryBeanTest()  throws Exception{
        environment.getProperty("");
        return springService.factoryBeanTest();
    }

    @RequestMapping("/beanLifeCycleTest")
    public void beanLifeCycleTest() {
        Person person = (Person)context.getBean("person");
        System.out.println(person);

        //System.out.println("现在开始关闭容器！");
        //context.destroy();
    }

    @RequestMapping("/transactionTest")
    public void transactionTest() {
        springService.transactionTest();
    }
}
