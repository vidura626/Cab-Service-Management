package com.example.cabservice.dao.impl;

import com.example.cabservice.config.DatabaseConnectionManager;
import com.example.cabservice.dao.DriverDAO;
import com.example.cabservice.entity.Driver;
import com.example.cabservice.exceptions.DatabaseException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.exceptions.ValidationException;
import com.example.cabservice.enums.DriverStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {
    private final Connection connection;

    public DriverDAOImpl(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public void saveDriver(Driver driver) throws ValidationException {
        validateDriver(driver);
        String query = "INSERT INTO Driver (name, nic, address, dob, licence, driver_status, image, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getNic());
            stmt.setString(3, driver.getAddress());
            stmt.setDate(4, driver.getDob());
            stmt.setString(5, driver.getLicence());
            stmt.setString(6, driver.getStatus().toString());
            stmt.setString(7, driver.getImage());
            stmt.setBoolean(8, driver.isActive());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error saving driver", e);
        }
    }

    @Override
    public void updateDriver(Long id, Driver driver) throws NotFoundException, ValidationException {
        validateDriver(driver);
        String query = "UPDATE Driver SET name=?, nic=?, address=?, dob=?, licence=?, driver_status=?, image=?, isActive=? WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getNic());
            stmt.setString(3, driver.getAddress());
            stmt.setDate(4, driver.getDob());
            stmt.setString(5, driver.getLicence());
            stmt.setString(6, driver.getStatus().toString());
            stmt.setString(7, driver.getImage());
            stmt.setBoolean(8, driver.isActive());
            stmt.setLong(9, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) throw new NotFoundException("Driver not found with ID: " + id);
        } catch (SQLException e) {
            throw new DatabaseException("Error updating driver", e);
        }
    }

    @Override
    public void deleteDriver(Long id) throws NotFoundException {
        String query = "DELETE FROM Driver WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) throw new NotFoundException("Driver not found with ID: " + id);
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting driver", e);
        }
    }

    @Override
    public void inactiveDriver(Long id) throws NotFoundException {
        String query = "UPDATE Driver SET isActive=FALSE WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) throw new NotFoundException("Driver not found with ID: " + id);
        } catch (SQLException e) {
            throw new DatabaseException("Error deactivating driver", e);
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM Driver";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                drivers.add(mapResultSetToDriver(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving drivers", e);
        }
        return drivers;
    }

    @Override
    public List<Driver> getAllDriversByStatus(String status) {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM Driver WHERE driver_status=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                drivers.add(mapResultSetToDriver(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving drivers by status", e);
        }
        return drivers;
    }

    @Override
    public List<Driver> getAllAvailableDriversByVehicleType(Long vehicleTypeId) {
        List<Driver> drivers = new ArrayList<>();
        String query = """
                SELECT d.* FROM Driver d
                JOIN DriverVehicleMapping dvm ON d.id = dvm.driver_id
                WHERE dvm.vehicle_type_id = ? AND d.driver_status = 'AVAILABLE' AND d.isActive = TRUE
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, vehicleTypeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                drivers.add(mapResultSetToDriver(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving availablek drivers by vehicle type", e);
        }
        return drivers;
    }

    @Override
    public void saveDriverImage(Long id, String image) throws NotFoundException {
        String query = "UPDATE Driver SET image=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, image);
            stmt.setLong(2, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) throw new NotFoundException("Driver not found with ID: " + id);
        } catch (SQLException e) {
            throw new DatabaseException("Error saving driver image", e);
        }
    }

    @Override
    public String getImage(Long id) throws NotFoundException {
        String query = "SELECT image FROM Driver WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("image");
            } else {
                throw new NotFoundException("Driver not found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving driver image", e);
        }
    }

    private void validateDriver(Driver driver) throws ValidationException {
        if (driver.getName() == null || driver.getName().isEmpty()) throw new ValidationException("Driver name is required.");
        if (driver.getNic() == null || driver.getNic().isEmpty()) throw new ValidationException("NIC is required.");
        if (driver.getLicence() == null || driver.getLicence().isEmpty()) throw new ValidationException("License is required.");
        if (driver.getStatus() == null) throw new ValidationException("Driver status must be specified.");
    }

    private Driver mapResultSetToDriver(ResultSet rs) throws SQLException {
        return new Driver(
                rs.getString("name"),
                rs.getString("nic"),
                rs.getString("address"),
                rs.getDate("dob"),
                rs.getString("licence"),
                DriverStatus.valueOf(rs.getString("driver_status")),
                rs.getString("image"),
                rs.getBoolean("isActive"),
                rs.getString("created_by"),
                rs.getString("updated_by"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
