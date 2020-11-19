package com.java.study.spring.bean.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author zibiao cao
 * @date 2020/11/18 11:02
 * @Version 1.0
 */
@Configuration
public class ConfigTest {
    @Bean
    public ConfigBean configBean() {

        ConfigBean configBean = new ConfigBean();
        System.out.println(configBean + "-----------@Component");
        return configBean;
    }

    @Bean
    ConfigBean2 configBean2() {
        return new ConfigBean2(configBean());
    }
}
