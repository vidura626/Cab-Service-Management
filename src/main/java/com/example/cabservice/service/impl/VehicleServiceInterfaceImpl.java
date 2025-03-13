package com.example.cabservice.service.impl;

import com.example.cabservice.dao.VehicleDAOInterface;
import com.example.cabservice.dto.request.VehicleRequestDto;
import com.example.cabservice.dto.response.VehicleResponseDto;
import com.example.cabservice.entity.Vehicle;
import com.example.cabservice.enums.JsonDtoMappingTypes;
import com.example.cabservice.enums.VehicleStatus;
import com.example.cabservice.exceptions.AlreadyAvailableException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.factory.JsonDtoMappingFactory;
import com.example.cabservice.service.VehicleServiceInterface;
import com.example.cabservice.util.JsonDtoMappingInterface;

public class VehicleServiceInterfaceImpl implements VehicleServiceInterface {
    private VehicleDAOInterface vehicleDAO;

    private final JsonDtoMappingInterface<VehicleRequestDto, Vehicle, VehicleResponseDto> mapping
            = JsonDtoMappingFactory.createJsonDtoMapping(JsonDtoMappingTypes.VEHICLE);

    public VehicleServiceInterfaceImpl(VehicleDAOInterface vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    @Override
    public void addVehicle(VehicleRequestDto vehicleRequestDto) throws Exception {
        Vehicle vehicle = mapping.toEntity(vehicleRequestDto);
        if (vehicleDAO.existsByLicence(vehicle.getLicensePlate())) {
            throw new AlreadyAvailableException("Vehicle with this license plate already exists");
        }
        vehicleDAO.createVehicle(vehicle);
    }

    @Override
    public void updateVehicle(VehicleRequestDto vehicleRequestDto, int id) throws Exception {
        Vehicle vehicle = mapping.toEntity(vehicleRequestDto, Long.valueOf(id));

        if (vehicleDAO.getVehicleById(id) == null) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }

        vehicleDAO.updateVehicle(vehicle);
    }

    @Override
    public void deleteVehicle(int id) throws Exception {
        if (!vehicleDAO.existsById(id)) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }

        vehicleDAO.deleteVehicle(id);
    }

    @Override
    public VehicleResponseDto getVehicleById(int id) throws Exception {
        Vehicle vehicle = vehicleDAO.getVehicleById(id);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found with id: " + id);
        }
        return mapping.toResponseDto(vehicle);
    }
}
