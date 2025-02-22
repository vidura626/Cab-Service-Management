package com.example.cabservice.service;

import com.example.cabservice.dto.VehicleDTO;
import java.sql.SQLException;
import java.util.List;

public interface VehicleServiceInterface {
    void addVehicle(VehicleDTO vehicleDTO) throws SQLException;
    void updateVehicle(VehicleDTO vehicleDTO, int id) throws SQLException;
    VehicleDTO getVehicleById(int id) throws SQLException;
    List<VehicleDTO> getAllVehicles() throws SQLException;
    void deleteVehicle(int id) throws SQLException;
}
