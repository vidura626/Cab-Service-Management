package com.example.cabservice.config;

import java.sql.*;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;

    private DatabaseConnectionManager()  {
        System.out.println("Logger: DatabaseConnectionManager.Constructor - Start");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cab_service?createDatabaseIfNotExist=true", "root", "1234");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Logger: DatabaseConnectionManager.Constructor - Error occured while connecting to database:" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        System.out.println("Logger: DatabaseConnectionManager.Constructor - End");
    }

    public static synchronized DatabaseConnectionManager getInstance() {
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
