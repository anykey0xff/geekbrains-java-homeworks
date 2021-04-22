package ru.geekbrains.java.part3.lesson3.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chat_db",
                    "root",
                    "secret"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
