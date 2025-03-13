package com.example.cabservice.entity;

import com.example.cabservice.enums.DriverStatus;

import java.sql.Date;
import java.time.LocalDateTime;

public class Driver extends BaseEntity {
    private int id;
    private String name;
    private String nic;
    private String address;
    private Date dob;
    private String licence;
    private DriverStatus status;
    private String image;
    private boolean isActive;

    private Driver(Builder builder) {
        super(builder.createdBy, builder.updatedBy, builder.createdDate, builder.updatedDate);
        this.id = builder.id;
        this.name = builder.name;
        this.nic = builder.nic;
        this.address = builder.address;
        this.dob = builder.dob;
        this.licence = builder.licence;
        this.status = builder.status;
        this.image = builder.image;
        this.isActive = builder.isActive;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static class Builder {
        private String createdBy;
        private String updatedBy;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private int id;
        private String name;
        private String nic;
        private String address;
        private Date dob;
        private String licence;
        private DriverStatus status;
        private String image;
        private boolean isActive;

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

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder nic(String nic) {
            this.nic = nic;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder dob(Date dob) {
            this.dob = dob;
            return this;
        }

        public Builder licence(String licence) {
            this.licence = licence;
            return this;
        }

        public Builder status(DriverStatus status) {
            this.status = status;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Driver build() {
            return new Driver(this);
        }
    }
}
