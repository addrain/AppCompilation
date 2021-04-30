package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration {
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/table?useSSL=false", "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
