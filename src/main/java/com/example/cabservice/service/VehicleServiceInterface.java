package com.example.cabservice.service;

import com.example.cabservice.dto.VehicleDTO;

public interface VehicleServiceInterface {
    void addVehicle(VehicleDTO vehicleDTO) throws Exception;
    void updateVehicle(VehicleDTO vehicleDTO, int id) throws Exception;
    void deleteVehicle(int id) throws Exception;
    VehicleDTO getVehicleById(int id) throws Exception;
}
