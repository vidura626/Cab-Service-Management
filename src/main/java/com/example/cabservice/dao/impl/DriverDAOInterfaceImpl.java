package com.example.cabservice.dao.impl;

import com.example.cabservice.dao.DriverDAOInterface;
import com.example.cabservice.entity.Driver;
import com.example.cabservice.exceptions.AlreadyAvailableException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.util.enums.DriverStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOInterfaceImpl implements DriverDAOInterface {
    private Connection connection;

    public DriverDAOInterfaceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createDriver(Driver driver) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM cab_service.driver WHERE nic = ? OR licence = ?";
        try (PreparedStatement stmt = connection.prepareStatement(checkQuery)) {
            stmt.setString(1, driver.getNic());
            stmt.setString(2, driver.getLicence());
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new AlreadyAvailableException("Driver with this NIC or Licence already exists.");
            }
        }

        String query = "INSERT INTO cab_service.driver (name, nic, address, dob, licence, status, image, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getNic());
            stmt.setString(3, driver.getAddress());
            stmt.setDate(4, driver.getDob());  // Use java.sql.Date
            stmt.setString(5, driver.getLicence());
            stmt.setString(6, driver.getStatus().getStatus());
            stmt.setString(7, driver.getImage());
            stmt.setBoolean(8, driver.isActive());
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
                driver.setNic(rs.getString("nic"));
                driver.setAddress(rs.getString("address"));
                driver.setDob(rs.getDate("dob"));
                driver.setLicence(rs.getString("licence"));
                System.out.println(rs.getString("status"));
                driver.setStatus(DriverStatus.fromString(rs.getString("status")));
                driver.setImage(rs.getString("image"));
                driver.setActive(rs.getBoolean("isActive"));
                return driver;
            }
            return null;
        }
    }

    @Override
    public void updateDriver(Driver driver) throws SQLException, NotFoundException {
        String query = "UPDATE cab_service.driver SET name = ?, address = ?, dob = ?, licence = ?, status = ?, image = ?, isActive = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getAddress());
            stmt.setDate(3, driver.getDob());
            stmt.setString(4, driver.getLicence());
            stmt.setString(5, driver.getStatus().getStatus());
            stmt.setString(6, driver.getImage());
            stmt.setBoolean(7, driver.isActive());
            stmt.setInt(8, driver.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotFoundException("Driver not found with id: " + driver.getId());
            }
        }
    }

    @Override
    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> driverList = new ArrayList<>();
        String query = "SELECT * FROM cab_service.driver";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setId(rs.getInt("id"));
                driver.setName(rs.getString("name"));
                driver.setNic(rs.getString("nic"));
                driver.setAddress(rs.getString("address"));
                driver.setDob(rs.getDate("dob"));
                driver.setLicence(rs.getString("licence"));
                driver.setStatus(DriverStatus.fromString(rs.getString("status")));
                driver.setImage(rs.getString("image"));
                driver.setActive(rs.getBoolean("isActive"));
                driverList.add(driver);
            }
        }
        return driverList;
    }

    @Override
    public void deleteDriver(int driverId) throws SQLException, NotFoundException {
        String query = "DELETE FROM cab_service.driver WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new NotFoundException("Driver not found with id: " + driverId);
            }
        }
    }

    @Override
    public void setActiveStatus(int driverId, boolean isActive) throws SQLException {
        String query = "UPDATE cab_service.driver SET isActive = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, isActive);
            stmt.setInt(2, driverId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void uploadImage(int driverId, String image) throws SQLException {
        String query = "UPDATE cab_service.driver SET image = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, image);
            stmt.setInt(2, driverId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void changeStatus(int driverId, String status) throws SQLException {
        String query = "UPDATE cab_service.driver SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, driverId);
            stmt.executeUpdate();
        }
    }
}

