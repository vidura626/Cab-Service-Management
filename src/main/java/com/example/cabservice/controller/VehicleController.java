package com.example.cabservice.controller;

import com.example.cabservice.factory.VehicleFactory;
import com.example.cabservice.service.VehicleServiceInterface;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

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
}
