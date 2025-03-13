package com.example.cabservice.controller;

import com.example.cabservice.dto.response.VehicleTypeResponseDto;
import com.example.cabservice.enums.JsonDtoMappingTypes;
import com.example.cabservice.factory.JsonDtoMappingFactory;
import com.example.cabservice.factory.VehicleTypeFactory;
import com.example.cabservice.service.VehicleTypeServiceInterface;
import com.example.cabservice.util.JsonDtoMappingInterface;
import com.example.cabservice.util.static_utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/vehicle-type")
public class VehicleTypeController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(VehicleTypeController.class.getName());
    private VehicleTypeServiceInterface vehicleTypeService;

    private JsonDtoMappingInterface vehicleTypeMapping;


    @Override
    public void init() throws ServletException {
        LOGGER.info("VehicleTypeController initialized");
        try {
            vehicleTypeService = VehicleTypeFactory.createVehicleTypeService();
            vehicleTypeMapping = JsonDtoMappingFactory.createJsonDtoMapping(JsonDtoMappingTypes.DRIVER_VEHICLE_TYPE);
            LOGGER.info("VehicleTypeController initialized successfully");
        } catch (SQLException e) {
            LOGGER.severe("DB connection error: " + e.getMessage());
            throw new ServletException("DB connection error" + e.getMessage());
        } catch (ExecutionControl.NotImplementedException e) {
            LOGGER.severe("Error while creating JsonDtoMapping : " + e.getMessage());
            throw new ServletException("Error while creating JsonDtoMapping : " + e.getMessage());
        }
    }

    /**
     * Handles fetching a vehicle type by ID.
     * Example: GET /vehicle-type?id=123
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");

        try {
            if (idParam != null) {
                // Fetch by ID
                int id = Integer.parseInt(idParam);
                LOGGER.info("Fetching vehicle type with ID: " + id);

                VehicleTypeResponseDto vehicleType = vehicleTypeService.getVehicleTypeById(id);
                if (vehicleType != null) {
                    response.setStatus(HttpServletResponse.SC_OK); // 200 OK
                    response.getWriter().write(vehicleTypeMapping.responseDtoToJsonString(vehicleType));
                    LOGGER.info("Successfully fetched vehicle type with ID: " + id);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
                    response.getWriter().write("{\"error\":\"Vehicle type not found\"}");
                    LOGGER.warning("Vehicle type with ID " + id + " not found");
                }
            } else {
                // Fetch all vehicle types
                LOGGER.info("Fetching all vehicle types");
                List<VehicleTypeResponseDto> vehicleTypes = vehicleTypeService.getAllVehicleTypes();

                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(JsonUtil.toJson(vehicleTypes));
                LOGGER.info("Successfully fetched all vehicle types");
            }
        } catch (NumberFormatException e) {
            LOGGER.severe("Invalid ID format: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid ID format\"}");
        } catch (Exception e) {
            LOGGER.severe("Error fetching vehicle type(s): " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error fetching vehicle type(s)\"}");
        }
    }

    /**
     * Handles adding a new vehicle type.
     * Example: POST /vehicle-type with JSON body.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Received POST request for /vehicle-type");
        String json = JsonUtil.getJsonFromRequest(request);
        LOGGER.info("JSON: " + json);
        VehicleTypeResponseDto vehicleTypeResponseDto = JsonUtil.parseVehicleTypeJson(json);

        try {
            if (vehicleTypeResponseDto.getDescription() == null || vehicleTypeResponseDto.getDescription().isEmpty()) {
                LOGGER.severe("Description is required");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 status
                response.getWriter().write("{\"error\":\"Description is required\"}");
                return;
            }

            vehicleTypeService.addVehicleType(vehicleTypeResponseDto);
            LOGGER.fine("Vehicle Type created successfully");
            response.setStatus(HttpServletResponse.SC_CREATED); // 201 status
            response.getWriter().write("{\"message\":\"Vehicle Type created successfully\"}");
        } catch (Exception ex) {
            LOGGER.severe("Error creating vehicle type: " + ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error creating vehicle type: " + ex.getMessage() + "\"}");
        }
    }

    /**
     * Handles updating an existing vehicle type.
     * Example: PUT /vehicle-type?id=123 with JSON body.
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Received PUT request for /vehicle-type");
        String json = JsonUtil.getJsonFromRequest(request);
        VehicleTypeResponseDto vehicleTypeResponseDto = JsonUtil.parseVehicleTypeJson(json);

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            if (vehicleTypeResponseDto.getDescription() == null || vehicleTypeResponseDto.getDescription().isEmpty()) {
                LOGGER.severe("Description is required");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"Description is required\"}");
                return;
            }

            vehicleTypeService.updateVehicleType(vehicleTypeResponseDto, id);
            LOGGER.info("Vehicle Type updated successfully");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Vehicle Type updated successfully\"}");
        } catch (NumberFormatException e) {
            LOGGER.severe("Invalid ID format: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid ID format\"}");
        } catch (Exception e) {
            LOGGER.severe("Error updating vehicle type: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error updating vehicle type\"}");
        }
    }
}
