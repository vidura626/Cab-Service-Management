package com.example.cabservice.dao;

import com.example.cabservice.entity.Driver;

import java.sql.SQLException;

public interface DriverDAOInterface {
    void createDriver(Driver driver) throws SQLException;
    Driver getDriverById(int id) throws SQLException;
    void updateDriver(Driver driver) throws SQLException;
}
