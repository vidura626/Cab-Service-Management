package com.example.cabservice.dao.impl;

import com.example.cabservice.dao.VehicleTypeDAOInterface;
import com.example.cabservice.entity.VehicleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleTypeDAOInterfaceImpl implements VehicleTypeDAOInterface {
    private Connection connection;

    public VehicleTypeDAOInterfaceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createVehicleType(VehicleType vehicleType) throws SQLException {
        if (existsByDescription(vehicleType.getDescription())) {
            throw new RuntimeException("Vehicle type with description '" + vehicleType.getDescription() + "' already exists.");
        }

        String query = "INSERT INTO vehicle_types (description) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicleType.getDescription());
            stmt.executeUpdate();
        }
    }


    @Override
    public void updateVehicleType(VehicleType vehicleType, int id) throws SQLException {
        if (!existsById(id)) {
            throw new RuntimeException("Vehicle type with ID " + id + " does not exist.");
        }

        String query = "UPDATE vehicle_types SET description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicleType.getDescription());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteVehicleType(int id) throws SQLException {
        // Check if the vehicle type is in use (you can customize this check based on your database design)
        if (checkUsage(id)) {
            throw new RuntimeException("Cannot delete vehicle type, it is currently in use.");
        }

        String query = "DELETE FROM vehicle_types WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    @Override
    public boolean checkUsage(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM vehicles WHERE vehicle_type_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
            return false;
        }
    }


    @Override
    public VehicleType getVehicleTypeById(int id) throws SQLException {
        String query = "SELECT * FROM vehicle_types WHERE id = ?";
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
    public boolean existsById(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM vehicle_types WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean existsByDescription(String description) throws SQLException {
        String query = "SELECT COUNT(*) FROM vehicle_types WHERE description = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, description);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
            return false;
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


    private VehicleType mapResultSetToVehicleType(ResultSet rs) throws SQLException {
        return new VehicleType.Builder()
                .id(rs.getLong("id"))
                .description(rs.getString("description"))
                .build();
    }
}
