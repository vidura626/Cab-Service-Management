package com.example.cabservice.controller;

import com.example.cabservice.dto.VehicleTypeDTO;
import com.example.cabservice.factory.VehicleTypeFactory;
import com.example.cabservice.service.VehicleTypeServiceInterface;
import com.example.cabservice.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/vehicle-type")
public class VehicleTypeController extends HttpServlet {
    VehicleTypeServiceInterface vehicleTypeService;

    @Override
    public void init() throws ServletException {
        try {
            vehicleTypeService = VehicleTypeFactory.createVehicleTypeService();
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    // POST Method (Create new vehicle type)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = JsonUtil.getJsonFromRequest(request);
        VehicleTypeDTO vehicleTypeDTO = JsonUtil.parseVehicleTypeJson(json);

        try {
            if (vehicleTypeDTO.getDescription() == null || vehicleTypeDTO.getDescription().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 status
                response.getWriter().write("{\"error\":\"Description is required\"}");
                return;
            }

            vehicleTypeService.addVehicleType(vehicleTypeDTO);
            response.setStatus(HttpServletResponse.SC_CREATED); // 201 status
            response.getWriter().write("{\"message\":\"Vehicle Type created successfully\"}");
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error creating vehicle type: " + ex.getMessage() + "\"}");
        }
    }

    // PUT Method (Update existing vehicle type)
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = JsonUtil.getJsonFromRequest(request);
        VehicleTypeDTO vehicleTypeDTO = JsonUtil.parseVehicleTypeJson(json);

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            if (vehicleTypeDTO.getDescription() == null || vehicleTypeDTO.getDescription().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 status
                response.getWriter().write("{\"error\":\"Description is required\"}");
                return;
            }

            vehicleTypeService. updateVehicleType(vehicleTypeDTO, id);
            response.setStatus(HttpServletResponse.SC_OK); // 200 status
            response.getWriter().write("{\"message\":\"Vehicle Type updated successfully\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error updating vehicle type: " + e.getMessage() + "\"}");
        }
    }
}
