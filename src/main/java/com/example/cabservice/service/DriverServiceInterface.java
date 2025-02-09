package com.example.cabservice.service;

import com.example.cabservice.dto.DriverDTO;

import java.sql.SQLException;
import java.util.List;

public interface DriverServiceInterface {
    void addDriver(DriverDTO driverDTO) throws SQLException;
    void updateDriver(DriverDTO driverDTO, int id) throws SQLException;

    DriverDTO getDriverById(int driverId) throws SQLException;

    List<DriverDTO> getAllDrivers();

    void deleteDriver(int driverId) throws SQLException;
}

