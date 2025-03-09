package com.example.cabservice.dto;

public class DriverDTO {

    private String name;
    private String address;
    private String dob;

    public DriverDTO(){}
    private DriverDTO(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.dob = builder.dob;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static class Builder {
        private String name;
        private String address;
        private String dob;

        public Builder(){
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }


        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }


        public Builder setDob(String dob) {
            this.dob = dob;
            return this;
        }

        public DriverDTO build() {
            return new DriverDTO(this);
        }
    }
}
