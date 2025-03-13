package com.example.cabservice.dao;

import com.example.cabservice.entity.Driver;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.exceptions.ValidationException;

import java.util.List;

public interface DriverDAO {
    void saveDriver(Driver driver) throws ValidationException;
    void updateDriver(Long id, Driver driver) throws NotFoundException, ValidationException;
    void deleteDriver(Long id) throws NotFoundException;
    void inactiveDriver(Long id) throws NotFoundException;
    List<Driver> getAllDrivers();
    List<Driver> getAllDriversByStatus(String status);
    List<Driver> getAllAvailableDriversByVehicleType(Long vehicleTypeId);
    void saveDriverImage(Long id, String image) throws NotFoundException;
    String getImage(Long id) throws NotFoundException;
}
