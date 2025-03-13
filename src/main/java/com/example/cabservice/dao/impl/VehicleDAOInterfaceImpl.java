package com.example.cabservice.dao.impl;

import com.example.cabservice.dao.VehicleDAOInterface;
import com.example.cabservice.dto.response.VehicleTypeResponseDto;
import com.example.cabservice.entity.Vehicle;
import com.example.cabservice.enums.VehicleStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOInterfaceImpl implements VehicleDAOInterface {
    private Connection connection;

    public VehicleDAOInterfaceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int createVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicles (make, model, year, licensePlate, fuelType, status, vehicle_type_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicle.getMake());
            stmt.setString(2, vehicle.getModel());
            stmt.setInt(3, vehicle.getYear());
            stmt.setString(4, vehicle.getLicensePlate());
            stmt.setString(5, vehicle.getFuelType());
            stmt.setString(6, vehicle.getStatus().name());
            stmt.setInt(7, vehicle.getVehicleTypeId());
            stmt.executeUpdate();

            try (ResultSet resultSet = stmt.getGeneratedKeys()) {
                if (resultSet.next()) return resultSet.getInt(1);
                else return -1;
            }
        }
    }

    @Override
    public Vehicle getVehicleById(int id) throws SQLException {
        String query = "SELECT ve.*, vt.description FROM vehicles ve left join vehicle_type vt on ve.vehicle_type_id = vt.id WHERE ve.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToVehicleType(rs);
            }
            return null;
        }
    }

    @Override
    public int updateVehicle(Vehicle vehicle) throws SQLException {
        String query = "UPDATE vehicles SET make = ?, model = ?, year = ?, licensePlate = ?, fuelType = ?, status = ?, vehicle_type_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicle.getMake());
            stmt.setString(2, vehicle.getModel());
            stmt.setInt(3, vehicle.getYear());
            stmt.setString(4, vehicle.getLicensePlate());
            stmt.setString(5, vehicle.getFuelType());
            stmt.setString(6, vehicle.getStatus().name());
            stmt.setInt(7, vehicle.getVehicleTypeId());
            stmt.setInt(8, vehicle.getId());
            return stmt.executeUpdate();
        }
    }

    @Override
    public void deleteVehicle(int id) throws SQLException {
        String query = "DELETE FROM vehicles WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Fetch all vehicles
    @Override
    public List<Vehicle> getAllVehicles() throws SQLException {
        String query = "SELECT ve.*, vt.description FROM vehicles ve left join vehicle_type vt on ve.vehicle_type_id = vt.id";
        List<Vehicle> vehicles = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(mapResultSetToVehicleType(rs));
            }
        }
        return vehicles;
    }

    // Fetch vehicles by vehicle type
    @Override
    public List<Vehicle> getAllVehiclesByType(int vehicleTypeId) throws SQLException {
        String query = "SELECT ve.*, vt.description FROM vehicles ve left join vehicle_type vt on ve.vehicle_type_id = vt.id WHERE ve.vehicle_type_id = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicleTypeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(mapResultSetToVehicleType(rs));
            }
        }
        return vehicles;
    }


    private Vehicle mapResultSetToVehicleType(ResultSet rs) throws SQLException {
        return new Vehicle.Builder()
                .id(rs.getInt("id"))
                .make(rs.getString("make"))
                .model(rs.getString("model"))
                .year(rs.getInt("year"))
                .licensePlate(rs.getString("licensePlate"))
                .fuelType(rs.getString("fuelType"))
                .status(VehicleStatus.valueOf(rs.getString("status")))
                .vehicleTypeId(rs.getInt("vehicle_type_id"))
                .vehicleTypeDescription(rs.getString("description"))
                .build();
    }

    // Fetch vehicles by status
    @Override
    public List<Vehicle> getAllVehiclesByStatus(VehicleStatus status) throws SQLException {
        String query = "SELECT ve.*, vt.description FROM vehicles ve left join vehicle_type vt on ve.vehicle_type_id = vt.id WHERE ve.status = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status.name());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(mapResultSetToVehicleType(rs));
            }
        }
        return vehicles;
    }
}
