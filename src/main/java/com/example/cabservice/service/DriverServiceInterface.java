package com.example.cabservice.service;

import com.example.cabservice.dto.DriverDTO;

import java.sql.SQLException;

public interface DriverServiceInterface {
    void addDriver(DriverDTO driverDTO) throws SQLException;
    void updateDriver(DriverDTO driverDTO, int id) throws SQLException;
}

