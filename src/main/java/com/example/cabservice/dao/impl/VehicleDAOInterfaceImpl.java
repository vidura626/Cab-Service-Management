package com.example.cabservice.dao.impl;

import com.example.cabservice.dao.VehicleDAOInterface;
import com.example.cabservice.entity.Vehicle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VehicleDAOInterfaceImpl implements VehicleDAOInterface {

    private Connection connection;

    public VehicleDAOInterfaceImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void createVehicle(Vehicle vehicle) throws SQLException {

    }

    @Override
    public void updateVehicle(Vehicle vehicle, int id) throws SQLException {

    }

    @Override
    public Vehicle getVehicleById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Vehicle> getAllVehicles() throws SQLException {
        return null;
    }

    @Override
    public void deleteVehicle(int id) throws SQLException {

    }
}
