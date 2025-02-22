package com.example.cabservice.dto;

import com.example.cabservice.enums.FuelTypes;
import java.util.List;

public class VehicleDTO {
    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private FuelTypes fuelType;
    private String status;
    private String owner;
    private List<String> vehicleImages;

    private VehicleDTO(Builder builder) {
        this.id = builder.id;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.fuelType = builder.fuelType;
        this.status = builder.status;
        this.owner = builder.owner;
        this.vehicleImages = builder.vehicleImages;
    }

    public Long getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getLicensePlate() { return licensePlate; }
    public FuelTypes getFuelType() { return fuelType; }
    public String getStatus() { return status; }
    public String getOwner() { return owner; }
    public List<String> getVehicleImages() { return vehicleImages; }

    public static class Builder {
        private Long id;
        private String make;
        private String model;
        private int year;
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

        public Builder setId(Long id) {
            this.id = id;
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

        public VehicleDTO build() {
            return new VehicleDTO(this);
        }
    }
}
