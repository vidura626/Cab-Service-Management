package com.example.cabservice.factory;

import com.example.cabservice.config.DatabaseConnectionManager;
import com.example.cabservice.dao.DriverDAO;
import com.example.cabservice.dao.VehicleDAOInterface;
import com.example.cabservice.dao.impl.DriverDAOImpl;
import com.example.cabservice.dao.impl.VehicleDAOInterfaceImpl;
import com.example.cabservice.service.DriverServiceInterface;
import com.example.cabservice.service.VehicleServiceInterface;
import com.example.cabservice.service.impl.DriverServiceInterfaceImpl;
import com.example.cabservice.service.impl.VehicleServiceInterfaceImpl;

import java.sql.SQLException;

public class DriverFactory {

    public static DriverDAO createDriverDao() throws SQLException {
        return new DriverDAOImpl(DatabaseConnectionManager.getInstance().getConnection());
    }

    public static DriverServiceInterface createDriverService() throws SQLException {
        return new DriverServiceInterfaceImpl(createDriverDao());
    }
}
