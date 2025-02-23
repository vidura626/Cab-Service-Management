package com.example.cabservice.dto;

public class VehicleDTO {
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private String fuelType;
    private String status;
    private int vehicleTypeId;

    private VehicleDTO(Builder builder) {
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.fuelType = builder.fuelType;
        this.status = builder.status;
        this.vehicleTypeId = builder.vehicleTypeId;
    }

    public static class Builder {
        private String make;
        private String model;
        private int year;
        private String licensePlate;
        private String fuelType;
        private String status;
        private int vehicleTypeId;

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

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setVehicleTypeId(int vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
            return this;
        }

        public VehicleDTO build() {
            return new VehicleDTO(this);
        }
    }

    // Getters and Setters (can be generated if needed)
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getLicensePlate() { return licensePlate; }
    public String getFuelType() { return fuelType; }
    public String getStatus() { return status; }
    public int getVehicleTypeId() { return vehicleTypeId; }
}
