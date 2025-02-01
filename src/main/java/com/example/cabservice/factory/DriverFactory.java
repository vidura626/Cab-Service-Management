package com.example.cabservice.factory;


import com.example.cabservice.config.DatabaseConnectionManager;
import com.example.cabservice.dao.DriverDAOInterface;
import com.example.cabservice.dao.impl.DriverDAOInterfaceImpl;
import com.example.cabservice.service.DriverServiceInterface;
import com.example.cabservice.service.impl.DriverServiceInterfaceImpl;

import java.sql.SQLException;

public class DriverFactory {

    public static DriverDAOInterface createDriverDAO() throws SQLException {
        return new DriverDAOInterfaceImpl(DatabaseConnectionManager.getInstance().getConnection());
    }

    public static DriverServiceInterface createDriverService() throws SQLException {
        return new DriverServiceInterfaceImpl(createDriverDAO());
    }
}
