package com.example.cabservice.service;

import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.dto.response.DriverResponseDto;
import com.example.cabservice.exceptions.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface DriverServiceInterface {
    void addDriver(DriverRequestDto driverDTO) throws SQLException;
    void updateDriver(DriverRequestDto driverDTO, int id) throws SQLException, NotFoundException;
    DriverResponseDto getDriverById(int driverId) throws SQLException;
    List<DriverResponseDto> getAllDrivers() throws SQLException;
    void deleteDriver(int driverId) throws SQLException, NotFoundException;
    void setActiveStatus(int driverId, boolean isActive) throws SQLException;
    void uploadImage(int driverId, String image) throws SQLException;
    void changeStatus(int driverId, String status) throws SQLException;
}
