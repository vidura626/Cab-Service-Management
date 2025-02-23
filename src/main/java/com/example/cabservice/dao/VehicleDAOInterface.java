package com.example.cabservice.dao;

import com.example.cabservice.entity.Vehicle;
import com.example.cabservice.enums.VehicleStatus;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAOInterface {
    int createVehicle(Vehicle vehicle) throws SQLException;
    Vehicle getVehicleById(int id) throws SQLException;
    int updateVehicle(Vehicle vehicle) throws SQLException;
    void deleteVehicle(int id) throws SQLException;

    // New methods to fetch all vehicles or filter them
    List<Vehicle> getAllVehicles() throws SQLException;
    List<Vehicle> getAllVehiclesByType(int vehicleTypeId) throws SQLException;
    List<Vehicle> getAllVehiclesByStatus(VehicleStatus status) throws SQLException;
}
