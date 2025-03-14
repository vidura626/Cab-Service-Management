package com.example.cabservice.factory;

import com.example.cabservice.dao.VehicleTypeDAOInterface;
import com.example.cabservice.dao.impl.VehicleTypeDAOInterfaceImpl;
import com.example.cabservice.service.VehicleTypeServiceInterface;
import com.example.cabservice.service.impl.VehicleTypeServiceInterfaceImpl;
import com.example.cabservice.config.DatabaseConnectionManager;

import java.sql.SQLException;

public class VehicleTypeFactory {
    public static VehicleTypeDAOInterface createVehicleTypeDAO() throws SQLException {
        return new VehicleTypeDAOInterfaceImpl(DatabaseConnectionManager.getInstance().getConnection());
    }

    public static VehicleTypeServiceInterface createVehicleTypeService() throws SQLException {
        return new VehicleTypeServiceInterfaceImpl(createVehicleTypeDAO());
    }
}
