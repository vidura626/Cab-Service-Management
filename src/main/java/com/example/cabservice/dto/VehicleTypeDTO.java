package com.example.cabservice.dto;

public class VehicleTypeDTO {
    private String description;

    private VehicleTypeDTO(Builder builder) {
        this.description = builder.description;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private String description;

        public Builder() {
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public VehicleTypeDTO build() {
            return new VehicleTypeDTO(this);
        }
    }
}
