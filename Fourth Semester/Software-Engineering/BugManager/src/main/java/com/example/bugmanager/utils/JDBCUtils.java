package com.example.bugmanager.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;

    public JDBCUtils() {
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            url = "jdbc:postgresql://localhost:5432/BugManager";
            user = "postgres";
            password = "admin";
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BugManager", "postgres","admin");
        } catch (SQLException e) {
            System.out.println("Error getting connection " + e);
        }

        return connection;
    }



    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }




}
