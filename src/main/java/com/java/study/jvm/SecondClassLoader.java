package com.java.study.jvm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author zibiao cao
 * @date 2020/11/27 9:38
 * @Version 1.0
 */
public class SecondClassLoader {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.130.176:3506/test", "root", "BigData@176");
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
