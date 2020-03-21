package com.krupa.maciej.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/have_i_been_crowned?useSSL=false&characterEncoding=utf8";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "coderslab";


    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}

