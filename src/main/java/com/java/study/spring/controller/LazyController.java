package com.java.study.spring.controller;

import com.java.study.spring.service.LazyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zibiao cao
 * @date 2020/11/13 13:49
 * @Version 1.0
 */
@Controller
public class LazyController {
    @Autowired
    private LazyService lazyService;

    @RequestMapping("getLazyBean")
    public String getLazyBean() {
        return lazyService.getLazyBean();
    }

    @RequestMapping("lazyDependent")
    public void lazyDependent() {
         lazyService.lazyDependent();
    }
}
