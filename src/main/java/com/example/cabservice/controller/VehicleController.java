package com.example.cabservice.controller;

import com.example.cabservice.dto.request.VehicleRequestDto;
import com.example.cabservice.exceptions.AlreadyAvailableException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.factory.VehicleFactory;
import com.example.cabservice.service.VehicleServiceInterface;
import com.example.cabservice.util.static_utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/vehicle")
public class VehicleController extends HttpServlet {
    private VehicleServiceInterface vehicleService;

    @Override
    public void init() throws ServletException {
        try {
            vehicleService = VehicleFactory.createVehicleService();
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    // POST method for creating vehicle
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
//        String json = JsonUtil.getJsonFromRequest(request);
//        VehicleRequestDto vehicleRequestDto = JsonUtil.parseVehicleJson(json);
//        try {
//            vehicleService.addVehicle(vehicleRequestDto);
//            response.setStatus(HttpServletResponse.SC_CREATED);
//            response.getWriter().write("{\"message\":\"Vehicle created successfully\"}");
//        } catch (AlreadyAvailableException e) {
//            response.setStatus(HttpServletResponse.SC_CONFLICT);
//            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"error\":\"Error creating vehicle\"}");
//        }
    }

    // PUT method for updating vehicle
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = JsonUtil.getJsonFromRequest(request);
//        VehicleRequestDto vehicleRequestDto = JsonUtil.parseVehicleJson(json);
//
//        int id = Integer.parseInt(request.getParameter("id"));
//        try {
//            vehicleService.updateVehicle(vehicleRequestDto, id);
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().write("{\"message\":\"Vehicle updated successfully\"}");
//        } catch (NotFoundException e) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            response.getWriter().write("{\"error\":\"Error updating vehicle\"}");
//        }
    }

    // Implement GET and DELETE methods similarly.
}
