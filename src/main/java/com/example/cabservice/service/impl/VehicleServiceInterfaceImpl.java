package com.example.cabservice.service.impl;

import com.example.cabservice.dao.DriverDAOInterface;
import com.example.cabservice.dao.VehicleDAOInterface;
import com.example.cabservice.dto.VehicleDTO;
import com.example.cabservice.service.VehicleServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class VehicleServiceInterfaceImpl implements VehicleServiceInterface {
    private VehicleDAOInterface driverDAO;

    public VehicleServiceInterfaceImpl(VehicleDAOInterface driverDAO) {
        this.driverDAO = driverDAO;
    }
    @Override
    public void addVehicle(VehicleDTO vehicleDTO) throws SQLException {

    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO, int id) throws SQLException {

    }

    @Override
    public VehicleDTO getVehicleById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() throws SQLException {
        return null;
    }

    @Override
    public void deleteVehicle(int id) throws SQLException {

    }
}
