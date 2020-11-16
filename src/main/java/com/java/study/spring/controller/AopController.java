package com.java.study.spring.controller;

import com.java.study.spring.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zibiao cao
 * @date 2020/11/13 9:41
 * @Version 1.0
 */
@RestController
public class AopController {
    @Autowired
    private AopService aopService;

    @RequestMapping("/test")
    public String test() {
        return aopService.test();
    }
}
