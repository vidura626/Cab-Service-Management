package com.example.cabservice.dao.impl;

import com.example.cabservice.dao.DriverDAOInterface;
import com.example.cabservice.entity.Driver;
import com.example.cabservice.exceptions.AlreadyAvailableException;
import com.example.cabservice.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DriverDAOInterfaceImpl implements DriverDAOInterface {
    private Connection connection;

    public DriverDAOInterfaceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createDriver(Driver driver) throws SQLException {

        String query = "SELECT COUNT(*) from cab_service.driver dr where dr.name=? and dr.address=? and dr.dob=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getAddress());
            stmt.setDate(3, driver.getDob());
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new AlreadyAvailableException("Driver already exists with the same details");
            }
        }
        query = "INSERT INTO cab_service.driver (name, address, dob) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getAddress());
            stmt.setDate(3, driver.getDob());
            stmt.executeUpdate();
        }
    }

    @Override
    public Driver getDriverById(int id) throws SQLException {
        String query = "SELECT * FROM cab_service.driver WHERE id = ?";
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
    public void updateDriver(Driver driver) throws SQLException, NotFoundException {
        String checkQuery = "SELECT COUNT(*) FROM cab_service.driver WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(checkQuery)) {
            statement.setInt(1, driver.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                throw new NotFoundException("Driver not found");
            }
        }

        StringBuilder query = new StringBuilder("UPDATE cab_service.driver SET ");
        List<Object> params = new ArrayList<>();

        if (driver.getName() != null) {
            query.append("name = ?, ");
            params.add(driver.getName());
        }
        if (driver.getAddress() != null) {
            query.append("address = ?, ");
            params.add(driver.getAddress());
        }
        if (driver.getDob() != null) {
            query.append("dob = ?, ");
            params.add(driver.getDob());
        }

        if (params.isEmpty()) {
            return;
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(driver.getId());

        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < params.size(); i++) {
                if (params.get(i) instanceof String) {
                    stmt.setString(i + 1, (String) params.get(i));
                } else if (params.get(i) instanceof java.sql.Date) {
                    stmt.setDate(i + 1, (java.sql.Date) params.get(i));
                } else if (params.get(i) instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) params.get(i));
                }
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        String query = "SELECT * FROM cab_service.driver";
        List<Driver> driverList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setId(rs.getInt("id"));
                driver.setName(rs.getString("name"));
                driver.setAddress(rs.getString("address"));
                driver.setDob(rs.getDate("dob"));
                driverList.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        }

        return driverList;
    }

    @Override
    public void deleteDriver(int driverId) throws SQLException, NotFoundException {
        // Check if the driver exists
        String checkQuery = "SELECT COUNT(*) FROM cab_service.driver WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(checkQuery)) {
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                // If no driver is found, throw a NotFoundException
                throw new NotFoundException("Driver not found with id: " + driverId);
            }
        }

        // Proceed to delete the driver if it exists
        String deleteQuery = "DELETE FROM cab_service.driver WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {
            stmt.setInt(1, driverId);
            int rowsAffected = stmt.executeUpdate();

            // If no rows were affected, the deletion did not succeed
            if (rowsAffected == 0) {
                throw new SQLException("Failed to delete driver with id: " + driverId);
            }
        } catch (SQLException e) {
            // Catch and rethrow SQLException if any issue occurs during deletion
            throw new SQLException("Error occurred while deleting the driver: " + e.getMessage(), e);
        }
    }

}
