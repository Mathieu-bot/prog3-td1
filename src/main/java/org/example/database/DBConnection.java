package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    String url = "jdbc:postgresql://localhost:5432/product_management_db";
    String user = "product_manager_user";
    String password = "123456";

    public Connection getDBConnection() throws Exception{
        return DriverManager.getConnection(url, user, password);
    }
}

