package com.java.study.spring.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Author zibiao cao
 * @date 2020/11/13 15:17
 * @Version 1.0
 */
@Component
@Lazy
public class LazyConfig2 {
    @Autowired
    LazyConfig lazyConfig;
}
