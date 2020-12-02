package com.java.study;

import org.mybatis.spring.annotation.MapperScan;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.java.study.spring.mapper")
public class StudyApplication {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        SpringApplication.run(StudyApplication.class, args);
    }

}
