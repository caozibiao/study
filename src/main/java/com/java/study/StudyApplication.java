package com.java.study;

import org.mybatis.spring.annotation.MapperScan;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.java.study.spring.mapper")
public class StudyApplication {

    public static void main(String[] args) throws Exception {

        JolTest obj1 = new JolTest();
        // 无锁打印
        System.out.println(ClassLayout.parseInstance(obj1).toPrintable());

        // HotSpot 虚拟机在启动后有个 4s 的延迟才会对每个新建的对象开启偏向锁
        //线程sleep 5s，确保虚拟机中偏向锁开启
        Thread.sleep(5000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                JolTest obj2 = new JolTest();
                synchronized (obj2){
                    //当前锁对象第一次被线程获取
                    System.out.println(ClassLayout.parseInstance(obj2).toPrintable());
                }
            }
        }).start();

        //轻量级锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj1){
                    //当前锁对象第一次被线程获取
                    System.out.println(ClassLayout.parseInstance(obj1).toPrintable());
                }
            }
        }).start();

        //重量级锁

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj1){
                    //当前锁对象第一次被线程获取
                    System.out.println(ClassLayout.parseInstance(obj1).toPrintable());
                }
            }
        }).start();



        //SpringApplication.run(StudyApplication.class, args);
    }

}

class JolTest{int i = 10;}
