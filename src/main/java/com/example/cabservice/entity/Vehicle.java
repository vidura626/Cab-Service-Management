package com.example.cabservice.entity;

import com.example.cabservice.enums.VehicleStatus;

public class Vehicle {
    private int id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private String fuelType;
    private VehicleStatus status;
    private int vehicleTypeId;
    private String vehicleTypeDescription;

    public void setId(int id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public void setVehicleTypeDescription(String vehicleTypeDescription) {
        this.vehicleTypeDescription = vehicleTypeDescription;
    }

    private Vehicle(Builder builder) {
        this.id = builder.id;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.fuelType = builder.fuelType;
        this.status = builder.status;
        this.vehicleTypeId = builder.vehicleTypeId;
        this.vehicleTypeDescription = builder.vehicleTypeDescription;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getLicensePlate() { return licensePlate; }
    public String getFuelType() { return fuelType; }
    public VehicleStatus getStatus() { return status; }
    public int getVehicleTypeId() { return vehicleTypeId; }
    public String getVehicleTypeDescription() { return vehicleTypeDescription; }

    public void setStatus(VehicleStatus status) { this.status = status; }

    public static class Builder {
        private int id;
        private String make;
        private String model;
        private int year;
        private String licensePlate;
        private String fuelType;
        private VehicleStatus status;  // Changed to VehicleStatus enum
        private int vehicleTypeId;
        private String vehicleTypeDescription;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Builder setStatus(VehicleStatus status) {
            this.status = status;
            return this;
        }

        public Builder setVehicleTypeId(int vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
            return this;
        }

        public Builder setVehicleTypeDescription(String vehicleTypeDescription) {
            this.vehicleTypeDescription = vehicleTypeDescription;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}
