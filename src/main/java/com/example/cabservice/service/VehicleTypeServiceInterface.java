package com.example.cabservice.service;

import com.example.cabservice.dto.request.VehicleTypeRequestDto;
import com.example.cabservice.dto.response.VehicleTypeResponseDto;

public interface VehicleTypeServiceInterface {
    void addVehicleType(VehicleTypeRequestDto vehicleTypeResponseDto) throws Exception;
    void updateVehicleType(VehicleTypeRequestDto vehicleTypeResponseDto, int id) throws Exception;

    VehicleTypeResponseDto getVehicleTypeById(int id);
}
