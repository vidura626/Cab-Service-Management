package com.example.cabservice.service.impl;

import com.example.cabservice.dao.DriverDAOInterface;
import com.example.cabservice.dto.DriverDTO;
import com.example.cabservice.entity.Driver;
import com.example.cabservice.service.DriverServiceInterface;

import java.sql.SQLException;

public class DriverServiceInterfaceImpl implements DriverServiceInterface {
    private DriverDAOInterface driverDAO;

    public DriverServiceInterfaceImpl(DriverDAOInterface driverDAO) {
        this.driverDAO = driverDAO;
    }

    @Override
    public void addDriver(DriverDTO driverDTO) throws SQLException {
        Driver driver = convertToEntity(driverDTO);
        driverDAO.createDriver(driver);
    }

    @Override
    public void updateDriver(DriverDTO driverDTO, int id) throws SQLException {
        Driver driver = convertToEntity(driverDTO);
        driver.setId(id);
        driverDAO.updateDriver(driver);
    }

    private Driver convertToEntity(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setAddress(driverDTO.getAddress());
        driver.setDob(java.sql.Date.valueOf(driverDTO.getDob())); // assuming dob is in "YYYY-MM-DD"
        return driver;
    }
}
