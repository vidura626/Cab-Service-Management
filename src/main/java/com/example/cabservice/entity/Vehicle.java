package com.example.cabservice.entity;

import com.example.cabservice.enums.VehicleStatus;

import java.sql.Date;

public class Vehicle extends BaseEntity {
    private int id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private String fuelType;
    private VehicleStatus status;
    private int vehicleTypeId;
    private String vehicleTypeDescription;

    private String image;

    public Vehicle(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
