package com.java.study.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zibiao cao
 * @date 2020/11/20 15:54
 * @Version 1.0
 */
@RestController
public class MybatisTest {
    /*@Autowired
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;*/
    @RequestMapping("/mybatisTest")
    public void mybatisTest() {
        /*sqlSessionFactory.openSession();
        Configuration configuration = sqlSessionFactory.getConfiguration();
        System.out.println(configuration);
        return configuration.toString();*/

        /*PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.130.176:3506/test");
        dataSource.setUsername("root");
        dataSource.setPassword("BigData@176");

        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("dev", transactionFactory, dataSource);
        Configuration configuration = new Configuration();

        configuration.getTypeAliasRegistry().registerAlias("role", Role.class);
        configuration.addMapper()

        sqlSessionFactoryBuilder.build()*/
    }
}
