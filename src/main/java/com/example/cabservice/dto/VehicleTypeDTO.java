package com.example.cabservice.dto;

public class VehicleTypeDTO {
    private Long id;
    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private VehicleTypeDTO(Builder builder) {
        this.description = builder.description;
    }

    public String getDescription() {
        return description;
    }
    public Long getId() {
        return id;
    }

    public static class Builder {
        private Long id;
        private String description;

        public Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }    public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public VehicleTypeDTO build() {
            return new VehicleTypeDTO(this);
        }
    }
}
