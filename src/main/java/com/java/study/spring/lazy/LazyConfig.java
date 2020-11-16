package com.java.study.spring.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Author zibiao cao
 * @date 2020/11/13 13:27
 * @Version 1.0
 */
@Component
@Lazy
public class LazyConfig {
    @Autowired
    LazyConfig2 lazyConfig2;
    public LazyConfig() {
        System.out.println("lazyConfig init");
    }

    public void lazy() {
        System.out.println("-------lazy-------");
    }
}
