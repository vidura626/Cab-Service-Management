package com.example.cabservice.entity;

import com.example.cabservice.enums.VehicleStatus;

import java.sql.Date;
import java.time.LocalDateTime;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleTypeDescription() {
        return vehicleTypeDescription;
    }

    public void setVehicleTypeDescription(String vehicleTypeDescription) {
        this.vehicleTypeDescription = vehicleTypeDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private Vehicle(Builder builder) {
        super(builder.createdBy, builder.updatedBy, builder.createdDate, builder.updatedDate);
        this.id = builder.id;
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.licensePlate = builder.licensePlate;
        this.fuelType = builder.fuelType;
        this.status = builder.status;
        this.vehicleTypeId = builder.vehicleTypeId;
        this.vehicleTypeDescription = builder.vehicleTypeDescription;
        this.image = builder.image;
    }
    public static class Builder {
        private String createdBy;
        private String updatedBy;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
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

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public LocalDateTime getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
        }

        public LocalDateTime getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public String getFuelType() {
            return fuelType;
        }

        public void setFuelType(String fuelType) {
            this.fuelType = fuelType;
        }

        public VehicleStatus getStatus() {
            return status;
        }

        public void setStatus(VehicleStatus status) {
            this.status = status;
        }

        public int getVehicleTypeId() {
            return vehicleTypeId;
        }

        public void setVehicleTypeId(int vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
        }

        public String getVehicleTypeDescription() {
            return vehicleTypeDescription;
        }

        public void setVehicleTypeDescription(String vehicleTypeDescription) {
            this.vehicleTypeDescription = vehicleTypeDescription;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder updatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public Builder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder updatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public Builder id(int id) {
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

        public Builder status(VehicleStatus status) {
            this.status = status;
            return this;
        }

        public Builder vehicleTypeId(int vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
            return this;
        }

        public Builder vehicleTypeDescription(String vehicleTypeDescription) {
            this.vehicleTypeDescription = vehicleTypeDescription;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}
