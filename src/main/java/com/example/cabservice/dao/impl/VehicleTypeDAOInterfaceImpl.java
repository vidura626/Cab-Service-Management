package com.example.cabservice.dao.impl;

import com.example.cabservice.dao.VehicleTypeDAOInterface;
import com.example.cabservice.entity.VehicleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleTypeDAOInterfaceImpl implements VehicleTypeDAOInterface {
    private Connection connection;
    private

    public VehicleTypeDAOInterfaceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createVehicleType(VehicleType vehicleType) throws SQLException {
        String query = "INSERT INTO vehicle_types (description) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicleType.getDescription());
            stmt.executeUpdate();
        }
    }

    @Override
    public VehicleType getVehicleTypeById(int id) throws SQLException {
        String query = "SELECT * FROM vehicle_types WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                VehicleType vehicleType = new VehicleType();
                vehicleType.setId(rs.getLong("id"));
                vehicleType.setDescription(rs.getString("description"));
                return vehicleType;
            }
            return null;
        }
    }

    @Override
    public void updateVehicleType(VehicleType vehicleType) throws SQLException {
        String query = "UPDATE vehicle_types SET description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicleType.getDescription());
            stmt.setInt(2, Math.toIntExact(vehicleType.getId()));
            stmt.executeUpdate();
        }
    }
}
