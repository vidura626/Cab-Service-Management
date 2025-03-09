package com.example.cabservice.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseSetup {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "cab-service-management-system";
    private static final String USER = "root";  // Update with your MySQL username
    private static final String PASSWORD = "1234";  // Update with your MySQL password

    // SQL query to create the database and tables
    private static final String CREATE_DB_QUERY = "CREATE DATABASE IF NOT EXISTS `" + DB_NAME + "`";

    private static final String CREATE_TABLES_QUERY = """
             USE `""\" + DB_NAME + ""\"`;
              CREATE TABLE IF NOT EXISTS Driver (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        license_number VARCHAR(255) NOT NULL UNIQUE,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                    );

                    CREATE TABLE IF NOT EXISTS Customer (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) UNIQUE,
                        phone_number VARCHAR(20),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                    );

                    CREATE TABLE IF NOT EXISTS Booking (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        customer_id BIGINT NOT NULL,
                        driver_id BIGINT NOT NULL,
                        vehicle_id BIGINT NOT NULL,
                        pickup_location VARCHAR(255),
                        dropoff_location VARCHAR(255),
                        booking_status ENUM('PENDING', 'ONGOING', 'FINISHED', 'CANCELLED') DEFAULT 'PENDING',
                        booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (customer_id) REFERENCES Customer(id),
                        FOREIGN KEY (driver_id) REFERENCES Driver(id),
                        FOREIGN KEY (vehicle_id) REFERENCES Vehicle(id)
                    );

                    CREATE TABLE IF NOT EXISTS DriverVehicleMapping (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        driver_id BIGINT NOT NULL,
                        vehicle_type_id BIGINT NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (driver_id) REFERENCES Driver(id),
                        FOREIGN KEY (vehicle_type_id) REFERENCES VehicleType(id)
                    );

                    CREATE TABLE IF NOT EXISTS Payment (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        booking_id BIGINT NOT NULL,
                        payment_method VARCHAR(50),
                        amount DECIMAL(10, 2) NOT NULL,
                        payment_status ENUM( 'CASH', 'ONLINE'),
                        payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (booking_id) REFERENCES Booking(id)
                    );

                    CREATE TABLE IF NOT EXISTS Vehicle (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        license_plate VARCHAR(255) NOT NULL UNIQUE,
                        make VARCHAR(255),
                        model VARCHAR(255),
                        year INT,
                        vehicle_status ENUM('AVAILABLE', 'IN_PROCESS','UNAVAILABLE') DEFAULT 'AVAILABLE',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                    );

                    CREATE TABLE IF NOT EXISTS VehicleType (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        type_name VARCHAR(255) NOT NULL,
                        description TEXT,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                    );

                    CREATE TABLE IF NOT EXISTS AuditLog (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        action_type VARCHAR(50) NOT NULL,
                        entity_name VARCHAR(255) NOT NULL,
                        entity_id BIGINT NOT NULL,
                        changed_by VARCHAR(255) NOT NULL,
                        changes TEXT,
                        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                    );

            """;

    // Method to create the database and tables
    public static void setupDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();

            // Create the database if it does not exist
            statement.executeUpdate(CREATE_DB_QUERY);
            System.out.println("Database created or already exists.");

            // Switch to the newly created database
            statement.executeUpdate("USE `" + DB_NAME + "`;");

            // Create tables
            statement.executeUpdate(CREATE_TABLES_QUERY);
            System.out.println("Tables created or already exist.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Run the database setup during application startup
        setupDatabase();
    }
}
