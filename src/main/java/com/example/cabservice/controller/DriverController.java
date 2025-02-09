package com.example.cabservice.controller;

import com.example.cabservice.dto.DriverDTO;
import com.example.cabservice.exceptions.AlreadyAvailableException;
import com.example.cabservice.exceptions.NotFoundException;
import com.example.cabservice.factory.DriverFactory;
import com.example.cabservice.service.DriverServiceInterface;
import com.example.cabservice.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/drivers") // Default URL for all drivers (GET all)
public class DriverController extends HttpServlet {
    private DriverServiceInterface driverService;

    @Override
    public void init() throws ServletException {
        try {
            driverService = DriverFactory.createDriverService();
        } catch (SQLException e) {
            throw new ServletException("DB connection error", e);
        }
    }

    // POST Method (Create new driver)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = JsonUtil.getJsonFromRequest(request);
        DriverDTO driverDTO = JsonUtil.parseDriverJson(json);
        try {
            driverService.addDriver(driverDTO);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"message\":\"Driver created successfully\"}");
        } catch (AlreadyAvailableException ex) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write("{\"error\":\""+ ex.getMessage() +"\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error creating driver\"}");
        }
    }

    // PUT Method (Update existing driver)
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = JsonUtil.getJsonFromRequest(request);
        DriverDTO driverDTO = JsonUtil.parseDriverJson(json);

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            driverService.updateDriver(driverDTO, id);
            response.setStatus(HttpServletResponse.SC_OK); // 200 status
            response.getWriter().write("{\"message\":\"Driver updated successfully\"}");
        } catch (NotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\""+ ex.getMessage() +"\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error updating driver\"}");
        }
    }

    // GET Method for fetching all drivers or a driver by ID
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driverIdParam = request.getParameter("id");

        if (driverIdParam != null) {
            // Get driver by ID
            try {
                int driverId = Integer.parseInt(driverIdParam);
                DriverDTO driver = driverService.getDriverById(driverId);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(JsonUtil.convertDriverToJson(driver)); // Assuming you have a utility to convert driver DTO to JSON
            } catch (NotFoundException ex) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\""+ ex.getMessage() +"\"}");
            } catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\":\""+ e.getMessage() +"\"}");
            }
        } else {
            // Get all drivers
            List<DriverDTO> drivers = driverService.getAllDrivers();
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(JsonUtil.convertDriversToJson(drivers)); // Assuming you have a utility to convert list of driver DTOs to JSON
        }
    }

    // DELETE Method (Delete a driver by ID)
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int driverId = Integer.parseInt(request.getParameter("id"));

        try {
            driverService.deleteDriver(driverId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Driver deleted successfully\"}");
        } catch (NotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\""+ ex.getMessage() +"\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error deleting driver\"}");
        }
    }
}
