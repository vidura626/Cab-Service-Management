package com.example.cabservice.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/cab_service?createDatabaseIfNotExist=true&serverTimezone=UTC";
    private static final String USER = "root";  // Update with your MySQL username
    private static final String PASSWORD = "1234"; // Update with your MySQL password
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private DatabaseConnectionManager() throws SQLException {
        try {
            Class.forName(DRIVER); // Ensure MySQL JDBC Driver is loaded
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL Database Successfully!");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found!", e);
        }
    }

    public static synchronized DatabaseConnectionManager getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error closing database connection: " + e.getMessage());
        }
    }
}