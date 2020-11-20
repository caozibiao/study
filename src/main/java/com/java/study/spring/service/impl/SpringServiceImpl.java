package com.java.study.spring.service.impl;

import com.java.study.spring.entity.po.Balance;
import com.java.study.spring.ioc.FactoryBeanDemo;
import com.java.study.spring.mapper.BalanceMapper;
import com.java.study.spring.service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zibiao cao
 * @date 2020/11/13 16:40
 * @Version 1.0
 */
@Service
public class SpringServiceImpl implements SpringService {
    @Autowired
    private BalanceMapper balanceMapper;
    @Override
    public String factoryBeanTest() throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanDemo.class);
        FactoryBeanDemo bean = (FactoryBeanDemo)context.getBean("factoryBeanDemo");
        System.out.println("-----------" + bean);
        FactoryBeanDemo bean1 = (FactoryBeanDemo)context.getBean("&factoryBeanDemo");
        return bean1.getObject().toString();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transactionTest() {
        Balance zhangsanBalance = new Balance();
        zhangsanBalance.setBalance(800);
        zhangsanBalance.setId(1);
        zhangsanBalance.setName("zhangsan");
        balanceMapper.updateById(zhangsanBalance);
        Balance lisiBalance = new Balance();
        lisiBalance.setBalance(1200);
        lisiBalance.setId(2);
        lisiBalance.setName("lisi");
        balanceMapper.updateById(lisiBalance);
    }
}
