package com.example.cabservice.dao;

import com.example.cabservice.entity.Vehicle;
import java.sql.SQLException;
import java.util.List;

public interface VehicleDAOInterface {
    void createVehicle(Vehicle vehicle) throws SQLException;
    void updateVehicle(Vehicle vehicle, int id) throws SQLException;
    Vehicle getVehicleById(int id) throws SQLException;
    List<Vehicle> getAllVehicles() throws SQLException;
    void deleteVehicle(int id) throws SQLException;
}
