package com.example.cabservice.dao;

import com.example.cabservice.entity.VehicleType;

import java.sql.SQLException;

public interface VehicleTypeDAOInterface {
    void createVehicleType(VehicleType vehicleType) throws SQLException;
    void updateVehicleType(VehicleType vehicleType, int id) throws SQLException;
    void deleteVehicleType(int id) throws SQLException;
    boolean checkUsage(int id) throws SQLException;
    VehicleType getVehicleTypeById(int id) throws SQLException;
    boolean existsById(int id) throws SQLException;
    boolean existsByDescription(String description) throws SQLException;
    void updateVehicleType(VehicleType vehicleType) throws SQLException;
}
