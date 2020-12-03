package com.java.study.jvm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SecondClassLoader {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test");
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
