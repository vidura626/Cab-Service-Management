package com.example.cabservice.dto.request;

public class VehicleRequestDto {

    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private String fuelType;
    private String status;
    private int vehicleTypeId;

    // Private constructor to ensure the object can only be created using the Builder
    private VehicleRequestDto(Builder builder) {
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.fuelType = builder.fuelType;
        this.status = builder.status;
        this.vehicleTypeId = builder.vehicleTypeId;
    }

    // Getter methods for each field
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getStatus() {
        return status;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    // Static inner Builder class
    public static class Builder {
        private String make;
        private String model;
        private int year;
        private String licensePlate;
        private String fuelType;
        private String status;
        private int vehicleTypeId;

        // Setter methods for the builder with method chaining
        public Builder make(String make) {
            this.make = make;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder licensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder fuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder vehicleTypeId(int vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
            return this;
        }

        // Build method to create a VehicleRequestDto object
        public VehicleRequestDto build() {
            return new VehicleRequestDto(this);
        }
    }
}
