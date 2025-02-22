package com.example.cabservice.service;

import com.example.cabservice.dto.VehicleTypeDTO;

public interface VehicleServiceInterface {
    void addVehicle(VehicleTypeDTO vehicleTypeDTO) throws Exception;
    void updateVehicleType(VehicleTypeDTO vehicleTypeDTO, int id) throws Exception;
    // Add additional methods for Delete and Read if necessary
}
