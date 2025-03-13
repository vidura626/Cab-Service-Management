package com.example.cabservice.dao.impl;

import com.example.cabservice.dao.BaseDAO;
import com.example.cabservice.dao.DriverDAO;
import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.dto.response.DriverResponseDto;
import com.example.cabservice.entity.Driver;
import com.example.cabservice.enums.JsonDtoMappingTypes;
import com.example.cabservice.exceptions.DatabaseException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.exceptions.ValidationException;
import com.example.cabservice.factory.JsonDtoMappingFactory;
import com.example.cabservice.util.JsonDtoMappingInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl extends BaseDAO<Driver> implements DriverDAO {
    private final Connection connection;
    JsonDtoMappingInterface<DriverRequestDto, Driver, DriverResponseDto> mapping =
            JsonDtoMappingFactory.createJsonDtoMapping(JsonDtoMappingTypes.DRIVER);

    public DriverDAOImpl(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    @Override
    public Driver mapResultSetToEntity(ResultSet rs) throws SQLException {
        return mapping.resultSetToResponseDto(rs);
    }

    @Override
    public void saveDriver(Driver driver) throws ValidationException, DatabaseException {
        validateDriver(driver);

        String query = "INSERT INTO driver (name, nic, address, dob, licence, driver_status, image, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            throw new DatabaseException("Error saving driver" + e.getMessage());
        }
    }


    @Override
    public void updateDriver(Long id, Driver driver) throws NotFoundException, ValidationException, DatabaseException {
        validateDriver(driver);

        String query = "UPDATE driver SET name=?, nic=?, address=?, dob=?, licence=?, driver_status=?, image=?, isActive=? WHERE id=?";

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
            throw new DatabaseException("Error updating driver" + e.getMessage());
        }
    }

    @Override
    public void deleteDriver(Long id) throws NotFoundException, DatabaseException {
        String query = "DELETE FROM driver WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) throw new NotFoundException("Driver not found with ID: " + id);
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting driver" + e.getMessage());
        }
    }

    @Override
    public void inactiveDriver(Long id) throws NotFoundException, DatabaseException {
        String query = "UPDATE driver SET isActive=FALSE WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) throw new NotFoundException("Driver not found with ID: " + id);
        } catch (SQLException e) {
            throw new DatabaseException("Error deactivating driver" + e.getMessage());
        }
    }

    @Override
    public List<Driver> getAllDrivers() throws DatabaseException {
        List<Driver> responseDtos = new ArrayList<>();
        String query = "SELECT * FROM driver";

        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                responseDtos.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving drivers" + e.getMessage());
        }

        return responseDtos;
    }


    @Override
    public List<Driver> getAllDriversByStatus(String status) throws DatabaseException {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM driver WHERE driver_status=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                drivers.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving drivers by status" + e.getMessage());
        }
        return drivers;
    }

    @Override
    public List<Driver> getAllAvailableDriversByVehicleType(Long vehicleTypeId) throws DatabaseException {
        List<Driver> drivers = new ArrayList<>();
        String query = """
                SELECT d.* FROM driver d
                JOIN driver_vehicle_mapping dvm ON d.id = dvm.driver_id
                WHERE dvm.vehicle_type_id = ? AND d.driver_status = 'AVAILABLE' AND d.isActive = TRUE
                """;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, vehicleTypeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                drivers.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving availablek drivers by vehicle type" + e.getMessage());
        }
        return drivers;
    }

    @Override
    public void saveDriverImage(Long id, String image) throws NotFoundException, DatabaseException {
        String query = "UPDATE driver SET image=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, image);
            stmt.setLong(2, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) throw new NotFoundException("Driver not found with ID: " + id);
        } catch (SQLException e) {
            throw new DatabaseException("Error saving driver image" + e.getMessage());
        }
    }

    @Override
    public String getImage(Long id) throws NotFoundException, DatabaseException {
        String query = "SELECT image FROM driver WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("image");
            } else {
                throw new NotFoundException("Driver not found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving driver image" + e.getMessage());
        }
    }

    @Override
    public Driver getById(Long id) {
        String query = "SELECT * FROM driver WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        String query = "SELECT COUNT(*) FROM driver WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsByNic(String nic) {
        String query = "SELECT COUNT(*) FROM driver WHERE nic=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nic);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void validateDriver(Driver driver) throws ValidationException {
        if (driver.getName() == null || driver.getName().isEmpty())
            throw new ValidationException("Driver name is required.");
        if (driver.getNic() == null || driver.getNic().isEmpty()) throw new ValidationException("NIC is required.");
        if (driver.getLicence() == null || driver.getLicence().isEmpty())
            throw new ValidationException("License is required.");
        if (driver.getStatus() == null) throw new ValidationException("Driver status must be specified.");
    }

}
