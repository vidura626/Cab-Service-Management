package com.example.cabservice.dao;

import com.example.cabservice.entity.Driver;
import com.example.cabservice.exceptions.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface DriverDAOInterface {
    void createDriver(Driver driver) throws SQLException;
    Driver getDriverById(int id) throws SQLException;
    void updateDriver(Driver driver) throws SQLException, NotFoundException;

    List<Driver> getAllDrivers();

    void deleteDriver(int driverId) throws SQLException;
}
