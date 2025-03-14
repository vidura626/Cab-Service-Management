package com.example.cabservice.service.impl;

import com.example.cabservice.dao.DriverDAO;
import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.dto.response.DriverResponseDto;
import com.example.cabservice.entity.Driver;
import com.example.cabservice.enums.JsonDtoMappingTypes;
import com.example.cabservice.exceptions.AlreadyAvailableException;
import com.example.cabservice.exceptions.DatabaseException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.exceptions.ValidationException;
import com.example.cabservice.factory.JsonDtoMappingFactory;
import com.example.cabservice.service.DriverServiceInterface;
import com.example.cabservice.util.JsonDtoMappingInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DriverServiceInterfaceImpl implements DriverServiceInterface {
    private DriverDAO driverDAO;
    private JsonDtoMappingInterface<DriverRequestDto, Driver, DriverResponseDto> driverMapping = JsonDtoMappingFactory.createJsonDtoMapping(JsonDtoMappingTypes.DRIVER);

    public DriverServiceInterfaceImpl(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    @Override
    public void addDriver(DriverRequestDto driverDTO) throws SQLException {
        try {
            if(driverDAO.existsByNic(driverDTO.getNic())) throw new AlreadyAvailableException("Driver with nic " + driverDTO.getNic() + " already exists");
            else driverDAO.saveDriver(driverMapping.toEntity(driverDTO));
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateDriver(DriverRequestDto driverDTO, int id) throws SQLException, NotFoundException {
        try {
            if(!driverDAO.existsById(Long.valueOf(id))) throw new NotFoundException("Driver not found with ID: " + id);
            Driver driver = driverMapping.toEntity(driverDTO);
            driver.setId(id);
            driverDAO.updateDriver(Long.valueOf(id),driver);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DriverResponseDto getDriverById(int driverId) throws SQLException {
        if(!driverDAO.existsById(Long.valueOf(driverId))) throw new NotFoundException("Driver not found with ID: " + driverId);
        Driver driver = driverDAO.getById(Long.valueOf(driverId));
        return driverMapping.toResponseDto(driver);
    }

    @Override
    public List<DriverResponseDto> getAllDrivers() throws SQLException {
        List<Driver> drivers = null;
        try {
            drivers = driverDAO.getAllDrivers();
            return drivers.stream().map(driverMapping::toResponseDto).collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDriver(int driverId) throws SQLException, NotFoundException {
        try {
            if(!driverDAO.existsById(Long.valueOf(driverId))) throw new NotFoundException("Driver not found with ID: " + driverId);
            driverDAO.deleteDriver(Long.valueOf(driverId));
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
