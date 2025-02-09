package com.example.cabservice.service.impl;

import com.example.cabservice.dao.DriverDAOInterface;
import com.example.cabservice.dto.DriverDTO;
import com.example.cabservice.entity.Driver;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.service.DriverServiceInterface;
import com.example.cabservice.util.Mapper;

import java.sql.SQLException;
import java.util.List;

public class DriverServiceInterfaceImpl implements DriverServiceInterface {
    private DriverDAOInterface driverDAO;

    public DriverServiceInterfaceImpl(DriverDAOInterface driverDAO) {
        this.driverDAO = driverDAO;
    }

    @Override
    public void addDriver(DriverDTO driverDTO) throws SQLException {
        driverDAO.createDriver(Mapper.toDriver(driverDTO));
    }

    @Override
    public void updateDriver(DriverDTO driverDTO, int id) throws SQLException, NotFoundException {
        Driver driver = Mapper.toDriver(driverDTO);
        driver.setId(id);
        driverDAO.updateDriver(driver);
    }

    @Override
    public DriverDTO getDriverById(int driverId) throws SQLException {
        return Mapper.toDriverDto(driverDAO.getDriverById(driverId));
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = driverDAO.getAllDrivers();
        return drivers.stream().map(Mapper::toDriverDto).toList();
    }

    @Override
    public void deleteDriver(int driverId) throws SQLException {
        driverDAO.deleteDriver(driverId);
    }
}
