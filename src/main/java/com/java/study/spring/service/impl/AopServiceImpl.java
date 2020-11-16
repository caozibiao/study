package com.java.study.spring.service.impl;

import com.java.study.spring.service.AopService;
import org.springframework.stereotype.Service;

/**
 * @Author zibiao cao
 * @date 2020/11/13 9:43
 * @Version 1.0
 */
@Service
public class AopServiceImpl implements AopService {
    @Override
    public String test() {
        return "test";
    }
}
