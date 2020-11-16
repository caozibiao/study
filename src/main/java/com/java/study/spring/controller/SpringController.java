package com.java.study.spring.controller;

import com.java.study.spring.service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/factoryBeanTest")
    public String factoryBeanTest()  throws Exception{
        return springService.factoryBeanTest();
    }
}
