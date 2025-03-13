package com.example.cabservice.dao;

import com.example.cabservice.entity.Driver;
import com.example.cabservice.exceptions.DatabaseException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.exceptions.ValidationException;

import java.util.List;

public interface DriverDAO {
    void saveDriver(Driver driver) throws ValidationException, DatabaseException;
    void updateDriver(Long id, Driver driver) throws NotFoundException, ValidationException, DatabaseException;
    void deleteDriver(Long id) throws NotFoundException, DatabaseException;
    void inactiveDriver(Long id) throws NotFoundException, DatabaseException;
    List<Driver> getAllDrivers() throws DatabaseException;
    List<Driver> getAllDriversByStatus(String status) throws DatabaseException;
    List<Driver> getAllAvailableDriversByVehicleType(Long vehicleTypeId) throws DatabaseException;
    void saveDriverImage(Long id, String image) throws NotFoundException, DatabaseException;
    String getImage(Long id) throws NotFoundException, DatabaseException;

    Driver getById(Long id);
    boolean existsById(Long id);
    boolean existsByNic(String nic);
}
