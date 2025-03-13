package com.example.cabservice.service.impl;

import com.example.cabservice.dao.VehicleTypeDAOInterface;
import com.example.cabservice.dto.request.VehicleTypeRequestDto;
import com.example.cabservice.dto.response.VehicleTypeResponseDto;
import com.example.cabservice.entity.VehicleType;
import com.example.cabservice.enums.JsonDtoMappingTypes;
import com.example.cabservice.factory.JsonDtoMappingFactory;
import com.example.cabservice.service.VehicleTypeServiceInterface;
import com.example.cabservice.util.JsonDtoMappingInterface;

import java.sql.SQLException;
import java.util.logging.Logger;

public class VehicleTypeServiceInterfaceImpl implements VehicleTypeServiceInterface {
    private VehicleTypeDAOInterface vehicleTypeDAO;
    private JsonDtoMappingInterface<VehicleTypeRequestDto, VehicleType, VehicleTypeResponseDto> vehicleTypeMapping
            = JsonDtoMappingFactory.createJsonDtoMapping(JsonDtoMappingTypes.VEHICLE_TYPE);
    private static Logger LOGGER = Logger.getLogger(VehicleTypeServiceInterfaceImpl.class.getName());

    public VehicleTypeServiceInterfaceImpl(VehicleTypeDAOInterface vehicleTypeDAO) {
        this.vehicleTypeDAO = vehicleTypeDAO;
    }

    @Override
    public void addVehicleType(VehicleTypeRequestDto requestDto) throws Exception {
        VehicleType vehicleType = vehicleTypeMapping.toEntity(requestDto);
        vehicleTypeDAO.createVehicleType(vehicleType);
    }

    @Override
    public void updateVehicleType(VehicleTypeRequestDto requestDto, int id) throws Exception {
        VehicleType vehicleType = vehicleTypeMapping.toEntity(requestDto, Long.valueOf(id));
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
