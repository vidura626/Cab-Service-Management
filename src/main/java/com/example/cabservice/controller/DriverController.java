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

@WebServlet("/driver")
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
        } catch (NotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error updating driver\"}");
        }
    }
}
