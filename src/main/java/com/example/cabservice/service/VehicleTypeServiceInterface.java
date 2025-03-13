package com.example.cabservice.service;

import com.example.cabservice.dto.response.VehicleTypeResponseDto;

public interface VehicleTypeServiceInterface {
    void addVehicleType(VehicleTypeResponseDto vehicleTypeResponseDto) throws Exception;
    void updateVehicleType(VehicleTypeResponseDto vehicleTypeResponseDto, int id) throws Exception;

    VehicleTypeResponseDto getVehicleTypeById(int id);
}
