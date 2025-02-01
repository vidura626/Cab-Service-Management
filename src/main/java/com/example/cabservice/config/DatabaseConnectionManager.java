package com.example.cabservice.config;

import java.sql.*;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;

    private DatabaseConnectionManager() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab_service?createDatabaseIfNotExist=true", "root", "1234");
    }

    public static synchronized DatabaseConnectionManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
