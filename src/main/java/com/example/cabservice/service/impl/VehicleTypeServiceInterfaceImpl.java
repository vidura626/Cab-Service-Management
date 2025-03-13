package com.example.cabservice.service.impl;

import com.example.cabservice.dao.VehicleTypeDAOInterface;
import com.example.cabservice.dto.response.VehicleTypeResponseDto;
import com.example.cabservice.entity.VehicleType;
import com.example.cabservice.service.VehicleTypeServiceInterface;

import java.sql.SQLException;
import java.util.logging.Logger;

public class VehicleTypeServiceInterfaceImpl implements VehicleTypeServiceInterface {
    private VehicleTypeDAOInterface vehicleTypeDAO;
    private static Logger LOGGER = Logger.getLogger(VehicleTypeServiceInterfaceImpl.class.getName());

    public VehicleTypeServiceInterfaceImpl(VehicleTypeDAOInterface vehicleTypeDAO) {
        this.vehicleTypeDAO = vehicleTypeDAO;
    }

    @Override
    public void addVehicleType(VehicleTypeResponseDto vehicleTypeResponseDto) throws Exception {
        VehicleType vehicleType = convertToEntity(vehicleTypeResponseDto);
        vehicleTypeDAO.createVehicleType(vehicleType);
    }

    @Override
    public void updateVehicleType(VehicleTypeResponseDto vehicleTypeResponseDto, int id) throws Exception {
        VehicleType vehicleType = convertToEntity(vehicleTypeResponseDto);
        vehicleType.setId(id);
        vehicleTypeDAO.updateVehicleType(vehicleType);
    }

    @Override
    public VehicleTypeResponseDto getVehicleTypeById(int id) {
        LOGGER.info("Getting vehicle type by id: " + id);
        VehicleType vehicleType = null;
        VehicleTypeResponseDto vehicleTypeResponseDto = new VehicleTypeResponseDto();

        try {
            vehicleType = vehicleTypeDAO.getVehicleTypeById(id);
            vehicleTypeResponseDto.setId(vehicleType.getId());
            vehicleTypeResponseDto.setDescription(vehicleType.getDescription());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicleTypeResponseDto;
    }

}
