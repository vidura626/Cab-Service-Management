//package com.example.cabservice.service.impl;
//
//import com.example.cabservice.dao.VehicleDAOInterface;
//import com.example.cabservice.dao.impl.VehicleDAOInterfaceImpl;
//import com.example.cabservice.entity.Vehicle;
//import com.example.cabservice.config.DatabaseConnectionManager;
//import com.example.cabservice.enums.VehicleStatus;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.*;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class VehicleServiceInterfaceImplTest {
//
//    private VehicleDAOInterface vehicleDAO;
//    private Connection connection;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        // Use the actual connection from DatabaseConnectionManager
//        connection = DatabaseConnectionManager.getInstance().getConnection();
//        vehicleDAO = new VehicleDAOInterfaceImpl(connection);
//
//        // Create tables for vehicle and vehicle_types (for testing purposes)
//        try (Statement stmt = connection.createStatement()) {
//            stmt.execute("""
//                    CREATE TABLE IF NOT EXISTS vehicle_types
//                    (
//                        id          INT AUTO_INCREMENT PRIMARY KEY,
//                        description VARCHAR(255) NOT NULL
//                    );
//                    """);
//            stmt.execute("""
//                  CREATE TABLE IF NOT EXISTS vehicles
//                      (
//                          id              INT AUTO_INCREMENT PRIMARY KEY,
//                          make            VARCHAR(255) NULL,
//                          model           VARCHAR(255) NULL,
//                          year            INT NULL,
//                          licensePlate    VARCHAR(255) NULL,
//                          fuelType        VARCHAR(50) NULL,
//                          status          ENUM('AVAILABLE', 'IN_PROCESS', 'UNAVAILABLE') NULL, -- Modified to ENUM type
//                          owner           VARCHAR(255) NULL,
//                          vehicle_type_id INT NULL,
//                          CONSTRAINT licensePlate UNIQUE (licensePlate),
//                          CONSTRAINT vehicles_vehicle_types_id_fk
//                              FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_types (id)
//                              ON UPDATE CASCADE ON DELETE CASCADE
//                      )
//                    """);
//        }
//    }
//
//    @AfterEach
//    public void tearDown() throws SQLException {
//        // Cleanup the database after each test
//        try (Statement stmt = connection.createStatement()) {
//            stmt.execute("DROP TABLE IF EXISTS vehicles");
//            stmt.execute("DROP TABLE IF EXISTS vehicle_types");
//        }
//        // Close the connection
//        DatabaseConnectionManager.getInstance().closeConnection();
//    }
//
//    @Test
//    public void testCreateVehicleWithValidVehicleType() throws SQLException {
//        // Insert a valid vehicle type first
//        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO vehicle_types (description) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setString(1, "Sedan");
//            stmt.executeUpdate();
//            ResultSet rs = stmt.getGeneratedKeys();
//            rs.next();
//            int vehicleTypeId = rs.getInt(1);
//
//            // Now, create a vehicle with the valid vehicle_type_id
//            Vehicle vehicle = new Vehicle.Builder()
//                    .setMake("Toyota")
//                    .setModel("Corolla")
//                    .setYear(2020)
//                    .setLicensePlate("ABC1234")
//                    .setFuelType("Petrol")
//                    .setStatus(VehicleStatus.AVAILABLE)
//                    .setVehicleTypeId(vehicleTypeId) // Using valid vehicle_type_id
//                    .build();
//
//            // Act
//            vehicleDAO.createVehicle(vehicle);
//
//            // Verify the vehicle has been created by querying the database
//            String query = "SELECT * FROM vehicles WHERE licensePlate = ?";
//            try (PreparedStatement selectStmt = connection.prepareStatement(query)) {
//                selectStmt.setString(1, vehicle.getLicensePlate());
//                ResultSet rs2 = selectStmt.executeQuery();
//                assertTrue(rs2.next());
//                assertEquals("Toyota", rs2.getString("make"));
//                assertEquals("Corolla", rs2.getString("model"));
//                assertEquals("AVAILABLE", rs2.getString("status")); // Verify the status is correctly stored as "AVAILABLE"
//            }
//        }
//    }
//
//    @Test
//    public void testCreateVehicleWithInvalidVehicleType() throws SQLException {
//        // Create a vehicle with an invalid vehicle_type_id (which does not exist)
//        Vehicle vehicle = new Vehicle.Builder()
//                .setMake("Toyota")
//                .setModel("Corolla")
//                .setYear(2020)
//                .setLicensePlate("ABC1234")
//                .setFuelType("Petrol")
//                .setStatus(VehicleStatus.AVAILABLE)
//                .setVehicleTypeId(9999) // Invalid vehicle_type_id that does not exist
//                .build();
//
//        // Act & Assert: This should throw an exception due to foreign key constraint violation
//        SQLException thrown = assertThrows(SQLException.class, () -> {
//            vehicleDAO.createVehicle(vehicle);
//        });
//
//        // Check if the exception message contains foreign key constraint error
//        assertTrue(thrown.getMessage().toLowerCase().contains("foreign key constraint fails"));
//    }
//
//    @Test
//    public void testGetVehicleById() throws SQLException {
//        // First, create a valid vehicle with an existing vehicle_type
//        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO vehicle_types (description) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setString(1, "SUV");
//            stmt.executeUpdate();
//            ResultSet rs = stmt.getGeneratedKeys();
//            rs.next();
//            int vehicleTypeId = rs.getInt(1);
//
//            Vehicle vehicle = new Vehicle.Builder()
//                    .setMake("Toyota")
//                    .setModel("Corolla")
//                    .setYear(2020)
//                    .setLicensePlate("ABC1234")
//                    .setFuelType("Petrol")
//                    .setStatus(VehicleStatus.AVAILABLE)
//                    .setVehicleTypeId(vehicleTypeId) // Valid vehicle_type_id
//                    .build();
//
//            vehicleDAO.createVehicle(vehicle);
//
//            // Now, retrieve it by ID
//            Vehicle retrievedVehicle = vehicleDAO.getVehicleById(1);
//
//            // Assert that the retrieved vehicle matches the created one
//            assertNotNull(retrievedVehicle);
//            assertEquals("Toyota", retrievedVehicle.getMake());
//            assertEquals("Corolla", retrievedVehicle.getModel());
//            assertEquals(VehicleStatus.AVAILABLE, retrievedVehicle.getStatus()); // Assert that status is correctly set
//        }
//    }
//
//    @Test
//    public void testUpdateVehicleWithValidVehicleType() throws SQLException {
//        // First, create a valid vehicle type and vehicle
//        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO vehicle_types (description) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setString(1, "Truck");
//            stmt.executeUpdate();
//            ResultSet rs = stmt.getGeneratedKeys();
//            rs.next();
//            int vehicleTypeId = rs.getInt(1);
//
//            Vehicle vehicle = new Vehicle.Builder()
//                    .setMake("Toyota")
//                    .setModel("Corolla")
//                    .setYear(2020)
//                    .setLicensePlate("ABC1234")
//                    .setFuelType("Petrol")
//                    .setStatus(VehicleStatus.AVAILABLE)
//                    .setVehicleTypeId(vehicleTypeId)
//                    .build();
//            int vehicleId = vehicleDAO.createVehicle(vehicle);
//
//            // Now, update the vehicle with the valid vehicle_type_id
//            vehicle.setId(vehicleId);
//            vehicle.setMake("Honda");
//            vehicle.setModel("Civic");
//            vehicle.setStatus(VehicleStatus.IN_PROCESS); // Updating status
//            vehicleDAO.updateVehicle(vehicle);
//
//            // Verify the update
//            String query = "SELECT * FROM vehicles WHERE id = ?";
//            try (PreparedStatement selectStmt = connection.prepareStatement(query)) {
//                selectStmt.setInt(1, vehicleId);
//                ResultSet rs2 = selectStmt.executeQuery();
//                assertTrue(rs2.next());
//                assertEquals("Honda", rs2.getString("make"));
//                assertEquals("Civic", rs2.getString("model"));
//                assertEquals("IN_PROCESS", rs2.getString("status")); // Assert that status is updated
//            }
//        }
//    }
//
//    @Test
//    public void testDeleteVehicle() throws SQLException {
//        // First, create a valid vehicle with an existing vehicle_type
//        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO vehicle_types (description) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setString(1, "Coupe");
//            stmt.executeUpdate();
//            ResultSet rs = stmt.getGeneratedKeys();
//            rs.next();
//            int vehicleTypeId = rs.getInt(1);
//
//            Vehicle vehicle = new Vehicle.Builder()
//                    .setMake("Toyota")
//                    .setModel("Corolla")
//                    .setYear(2020)
//                    .setLicensePlate("ABC1234")
//                    .setFuelType("Petrol")
//                    .setStatus(VehicleStatus.AVAILABLE)
//                    .setVehicleTypeId(vehicleTypeId)
//                    .build();
//            vehicleDAO.createVehicle(vehicle);
//
//            // Delete the vehicle
//            vehicleDAO.deleteVehicle(vehicle.getId());
//
//            // Verify the vehicle was deleted
//            String query = "SELECT * FROM vehicles WHERE id = ?";
//            try (PreparedStatement selectStmt = connection.prepareStatement(query)) {
//                selectStmt.setInt(1, vehicle.getId());
//                ResultSet rs2 = selectStmt.executeQuery();
//                assertFalse(rs2.next());
//            }
//        }
//    }
//
//    @Test
//    public void testGetAllVehicles() throws SQLException {
//        // First, insert some vehicles
//        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO vehicle_types (description) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setString(1, "Sedan");
//            stmt.executeUpdate();
//            ResultSet rs = stmt.getGeneratedKeys();
//            rs.next();
//            int vehicleTypeId = rs.getInt(1);
//
//            Vehicle vehicle1 = new Vehicle.Builder()
//                    .setMake("Toyota")
//                    .setModel("Camry")
//                    .setYear(2021)
//                    .setLicensePlate("XYZ1234")
//                    .setFuelType("Petrol")
//                    .setStatus(VehicleStatus.AVAILABLE)
//                    .setVehicleTypeId(vehicleTypeId)
//                    .build();
//            vehicleDAO.createVehicle(vehicle1);
//
//            Vehicle vehicle2 = new Vehicle.Builder()
//                    .setMake("Honda")
//                    .setModel("Accord")
//                    .setYear(2021)
//                    .setLicensePlate("XYZ5678")
//                    .setFuelType("Petrol")
//                    .setStatus(VehicleStatus.UNAVAILABLE)
//                    .setVehicleTypeId(vehicleTypeId)
//                    .build();
//            vehicleDAO.createVehicle(vehicle2);
//
//            // Fetch all vehicles
//            List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
//
//            // Verify vehicles are retrieved
//            assertEquals(2, vehicles.size());
//        }
//    }
//}
