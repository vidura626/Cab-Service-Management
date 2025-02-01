package com.example.cabservice.dao.impl;

import com.example.cabservice.dao.DriverDAOInterface;
import com.example.cabservice.entity.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DriverDAOInterfaceImpl implements DriverDAOInterface {
    private Connection connection;

    public DriverDAOInterfaceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createDriver(Driver driver) throws SQLException {

        String query = "INSERT INTO drivers (name, address, dob) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getAddress());
            stmt.setDate(3, driver.getDob());
            stmt.executeUpdate();
        }
    }

    @Override
    public Driver getDriverById(int id) throws SQLException {
        String query = "SELECT * FROM drivers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Driver driver = new Driver();
                driver.setId(rs.getInt("id"));
                driver.setName(rs.getString("name"));
                driver.setAddress(rs.getString("address"));
                driver.setDob(rs.getDate("dob"));
                return driver;
            }
            return null;
        }
    }

    @Override
    public void updateDriver(Driver driver) throws SQLException {
        String query = "UPDATE drivers SET name = ?, address = ?, dob = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getAddress());
            stmt.setDate(3, driver.getDob());
            stmt.setInt(4, driver.getId());
            stmt.executeUpdate();
        }
    }
}
