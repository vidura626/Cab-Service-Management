package com.example.cabservice.dao;

import com.example.cabservice.entity.VehicleType;

import java.sql.SQLException;

public interface VehicleTypeDAOInterface {
    void createVehicleType(VehicleType vehicleType) throws SQLException;
    VehicleType getVehicleTypeById(int id) throws SQLException;
    void updateVehicleType(VehicleType vehicleType) throws SQLException;
}
