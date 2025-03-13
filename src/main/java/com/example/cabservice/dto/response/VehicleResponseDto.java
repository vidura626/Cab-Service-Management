package com.example.cabservice.dto.response;

public class VehicleResponseDto {

    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private String fuelType;
    private String status;
    private int vehicleTypeId;

    // Private constructor to ensure that the object can only be created using the Builder
    private VehicleResponseDto(Builder builder) {
        this.id = builder.id;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.fuelType = builder.fuelType;
        this.status = builder.status;
        this.vehicleTypeId = builder.vehicleTypeId;
    }

    // Getter methods
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

    // Builder class
    public static class Builder {
        private Long id;
        private String make;
        private String model;
        private int year;
        private String licensePlate;
        private String fuelType;
        private String status;
        private int vehicleTypeId;

        // Setters for the builder fields (method chaining)
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        // Build the VehicleResponseDto object
        public VehicleResponseDto build() {
            return new VehicleResponseDto(this);
        }
    }
}
