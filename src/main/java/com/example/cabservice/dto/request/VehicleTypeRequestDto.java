package com.example.cabservice.dto.request;

public class VehicleTypeRequestDto {
    private String description;

    public VehicleTypeRequestDto() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private VehicleTypeRequestDto(Builder builder) {
        this.description = builder.description;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {
        private String description;

        public Builder() {
        }

        public Builder setId(Long id) {
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public VehicleTypeRequestDto build() {
            return new VehicleTypeRequestDto(this);
        }
    }
}
