package com.example.cabservice.service.impl;

import com.example.cabservice.dao.DriverDAOInterface;
import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.dto.response.DriverResponseDto;
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
    public void addDriver(DriverRequestDto driverDTO) throws SQLException {
        driverDAO.createDriver(Mapper.toDriver(driverDTO));
    }

    @Override
    public void updateDriver(DriverRequestDto driverDTO, int id) throws SQLException, NotFoundException {
        Driver driver = Mapper.toDriver(driverDTO);
        driver.setId(id);
        driverDAO.updateDriver(driver);
    }

    @Override
    public DriverResponseDto getDriverById(int driverId) throws SQLException {
        Driver driver = driverDAO.getDriverById(driverId);
        return Mapper.toDriverResponseDto(driver);
    }

    @Override
    public List<DriverResponseDto> getAllDrivers() throws SQLException {
        List<Driver> drivers = driverDAO.getAllDrivers();
        return drivers.stream().map(Mapper::toDriverResponseDto).toList();
    }

    @Override
    public void deleteDriver(int driverId) throws SQLException, NotFoundException {
        driverDAO.deleteDriver(driverId);
    }

    @Override
    public void setActiveStatus(int driverId, boolean isActive) throws SQLException {
        driverDAO.setActiveStatus(driverId, isActive);
    }

    @Override
    public void uploadImage(int driverId, String image) throws SQLException {
        driverDAO.uploadImage(driverId, image);
    }

    @Override
    public void changeStatus(int driverId, String status) throws SQLException {
        driverDAO.changeStatus(driverId, status);
    }
}
