package com.example.cabservice.entity;

import com.example.cabservice.dto.VehicleTypeDTO;
import com.example.cabservice.enums.FuelTypes;

import java.util.List;

public class Vehicle {
    private Long id;
    private String make;
    private String model;
    private int year;
    private VehicleTypeDTO vehicleType;
    private String licensePlate;
    private FuelTypes fuelType;
    private String status;
    private String owner;
    private List<String> vehicleImages;

    private Vehicle(Builder builder) {
        this.id = builder.vehicleId;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.vehicleType = builder.vehicleType;
        this.licensePlate = builder.licensePlate;
        this.fuelType = builder.fuelType;
        this.status = builder.status;
        this.owner = builder.owner;
        this.vehicleImages = builder.vehicleImages;
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public VehicleTypeDTO getVehicleType() {
        return vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public FuelTypes getFuelType() {
        return fuelType;
    }

    public String getStatus() {
        return status;
    }

    public String getOwner() {
        return owner;
    }

    public List<String> getVehicleImages() {
        return vehicleImages;
    }

    public static class Builder {
        private Long vehicleId;
        private String make;
        private String model;
        private int year;
        private VehicleTypeDTO vehicleType;
        private String licensePlate;
        private FuelTypes fuelType;
        private String status;
        private String owner;
        private List<String> vehicleImages;

        public Builder(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        public Builder setVehicleId(Long vehicleId) {
            this.vehicleId = vehicleId;
            return this;
        }

        public Builder setVehicleType(VehicleTypeDTO vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setFuelType(FuelTypes fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Builder setVehicleImages(List<String> vehicleImages) {
            this.vehicleImages = vehicleImages;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}
