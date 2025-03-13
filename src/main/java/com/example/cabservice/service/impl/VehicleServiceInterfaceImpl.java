package com.example.cabservice.service.impl;

import com.example.cabservice.dao.VehicleDAOInterface;
import com.example.cabservice.dto.request.VehicleRequestDto;
import com.example.cabservice.entity.Vehicle;
import com.example.cabservice.enums.VehicleStatus;
import com.example.cabservice.exceptions.AlreadyAvailableException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.service.VehicleServiceInterface;

public class VehicleServiceInterfaceImpl implements VehicleServiceInterface {
    private VehicleDAOInterface vehicleDAO;

    public VehicleServiceInterfaceImpl(VehicleDAOInterface vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    @Override
    public void addVehicle(VehicleRequestDto vehicleRequestDto) throws Exception {
        Vehicle vehicle = new Vehicle.Builder()
                .setMake(vehicleRequestDto.getMake())
                .setModel(vehicleRequestDto.getModel())
                .setYear(vehicleRequestDto.getYear())
                .setLicensePlate(vehicleRequestDto.getLicensePlate())
                .setFuelType(vehicleRequestDto.getFuelType())
                .setStatus(Enum.valueOf(VehicleStatus.class, vehicleRequestDto.getStatus()))
                .setVehicleTypeId(vehicleRequestDto.getVehicleTypeId())
                .build();

        // Check for duplicate license plate
        if (vehicleDAO.getVehicleById(vehicle.getId()) != null) {
            throw new AlreadyAvailableException("Vehicle with this license plate already exists");
        }

        vehicleDAO.createVehicle(vehicle);
    }

    @Override
    public void updateVehicle(VehicleRequestDto vehicleRequestDto, int id) throws Exception {
        Vehicle vehicle = new Vehicle.Builder()
                .setId(id)
                .setMake(vehicleRequestDto.getMake())
                .setModel(vehicleRequestDto.getModel())
                .setYear(vehicleRequestDto.getYear())
                .setLicensePlate(vehicleRequestDto.getLicensePlate())
                .setFuelType(vehicleRequestDto.getFuelType())
                .setStatus(Enum.valueOf(VehicleStatus.class, vehicleRequestDto.getStatus()))
                .setVehicleTypeId(vehicleRequestDto.getVehicleTypeId())
                .build();

        if (vehicleDAO.getVehicleById(id) == null) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }

        vehicleDAO.updateVehicle(vehicle);
    }

    @Override
    public void deleteVehicle(int id) throws Exception {
        if (vehicleDAO.getVehicleById(id) == null) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }

        vehicleDAO.deleteVehicle(id);
    }

    @Override
    public VehicleRequestDto getVehicleById(int id) throws Exception {
        Vehicle vehicle = vehicleDAO.getVehicleById(id);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }

        return new VehicleRequestDto.Builder()
                .setMake(vehicle.getMake())
                .setModel(vehicle.getModel())
                .setYear(vehicle.getYear())
                .setLicensePlate(vehicle.getLicensePlate())
                .setFuelType(vehicle.getFuelType())
                .setStatus(vehicle.getStatus().name())
                .setVehicleTypeId(vehicle.getVehicleTypeId())
                .build();
    }
}
