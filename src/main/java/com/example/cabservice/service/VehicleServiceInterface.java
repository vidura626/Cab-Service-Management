package com.example.cabservice.service;

import com.example.cabservice.dto.request.VehicleRequestDto;
import com.example.cabservice.dto.response.VehicleResponseDto;

public interface VehicleServiceInterface {
    void addVehicle(VehicleRequestDto vehicleRequestDto) throws Exception;
    void updateVehicle(VehicleRequestDto vehicleRequestDto, int id) throws Exception;
    void deleteVehicle(int id) throws Exception;
    VehicleResponseDto getVehicleById(int id) throws Exception;
}
