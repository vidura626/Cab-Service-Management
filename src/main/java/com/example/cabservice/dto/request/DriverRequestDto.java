package com.example.cabservice.dto.request;

import java.util.List;

public class DriverRequestDto {
    private String name;
    private List<Long> vehicleTypeId;
    private String nic;
    private String address;
    private String dob;
    private String licence;
    private String status;
    private String image;
    private boolean isActive;

    public DriverRequestDto(){

    }
    public DriverRequestDto(String name, List<Long> vehicleTypeId, String nic, String address, String dob, String licence, String status, String image, boolean isActive) {
        this.name = name;
        this.vehicleTypeId = vehicleTypeId;
        this.nic = nic;
        this.address = address;
        this.dob = dob;
        this.licence = licence;
        this.status = status;
        this.image = image;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(List<Long> vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    private DriverRequestDto(Builder builder) {
        this.name = builder.name;
        this.vehicleTypeId = builder.vehicleTypeId;
        this.nic = builder.nic;
        this.address = builder.address;
        this.dob = builder.dob;
        this.licence = builder.licence;
        this.status = builder.status;
        this.image = builder.image;
        this.isActive = builder.isActive;
    }


    // Builder Class
    public static class Builder {
        private String name;
        private List<Long> vehicleTypeId;
        private String nic;
        private String address;
        private String dob;
        private String licence;
        private String status;
        private String image;
        private boolean isActive;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder vehicleTypeId(List<Long> vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
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

        public Builder dob(String dob) {
            this.dob = dob;
            return this;
        }

        public Builder licence(String licence) {
            this.licence = licence;
            return this;
        }

        public Builder status(String status) {
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
        public DriverRequestDto build() {
            return new DriverRequestDto(this);
        }
    }
}
