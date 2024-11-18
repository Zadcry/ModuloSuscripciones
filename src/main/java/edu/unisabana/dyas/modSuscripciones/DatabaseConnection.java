package edu.unisabana.dyas.modSuscripciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/DATABASE";
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

