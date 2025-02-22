package com.example.cabservice.service.impl;

import com.example.cabservice.dao.VehicleTypeDAOInterface;
import com.example.cabservice.dto.VehicleTypeDTO;
import com.example.cabservice.entity.VehicleType;
import com.example.cabservice.service.VehicleTypeServiceInterface;

public class VehicleTypeServiceInterfaceImpl implements VehicleTypeServiceInterface {
    private VehicleTypeDAOInterface vehicleTypeDAO;

    public VehicleTypeServiceInterfaceImpl(VehicleTypeDAOInterface vehicleTypeDAO) {
        this.vehicleTypeDAO = vehicleTypeDAO;
    }

    @Override
    public void addVehicleType(VehicleTypeDTO vehicleTypeDTO) throws Exception {
        VehicleType vehicleType = convertToEntity(vehicleTypeDTO);
        vehicleTypeDAO.createVehicleType(vehicleType);
    }

    @Override
    public void updateVehicleType(VehicleTypeDTO vehicleTypeDTO, int id) throws Exception {
        VehicleType vehicleType = convertToEntity(vehicleTypeDTO);
        vehicleType.setId(id);
        vehicleTypeDAO.updateVehicleType(vehicleType);
    }

    private VehicleType convertToEntity(VehicleTypeDTO vehicleTypeDTO) {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setDescription(vehicleTypeDTO.getDescription());
        return vehicleType;
    }
}
