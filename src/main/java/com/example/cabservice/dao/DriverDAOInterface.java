package com.example.cabservice.dao;

import com.example.cabservice.entity.Driver;
import com.example.cabservice.exceptions.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface DriverDAOInterface {
    void createDriver(Driver driver) throws SQLException;
    Driver getDriverById(int id) throws SQLException;
    void updateDriver(Driver driver) throws SQLException, NotFoundException;
    List<Driver> getAllDrivers() throws SQLException;
    void deleteDriver(int driverId) throws SQLException, NotFoundException;
    void setActiveStatus(int driverId, boolean isActive) throws SQLException;
    void uploadImage(int driverId, String image) throws SQLException;
    void changeStatus(int driverId, String status) throws SQLException;
}
