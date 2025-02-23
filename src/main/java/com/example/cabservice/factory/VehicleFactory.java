package com.example.cabservice.factory;

import com.example.cabservice.config.DatabaseConnectionManager;
import com.example.cabservice.dao.VehicleDAOInterface;
import com.example.cabservice.dao.impl.VehicleDAOInterfaceImpl;
import com.example.cabservice.service.VehicleServiceInterface;
import com.example.cabservice.service.impl.VehicleServiceInterfaceImpl;

import java.sql.SQLException;

public class VehicleFactory {

    public static VehicleDAOInterface createVehicleDAO() throws SQLException {
        return new VehicleDAOInterfaceImpl(DatabaseConnectionManager.getInstance().getConnection());
    }

    public static VehicleServiceInterface createVehicleService() throws SQLException {
        return new VehicleServiceInterfaceImpl(createVehicleDAO());
    }
}
