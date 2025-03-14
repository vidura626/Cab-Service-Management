package com.example.cabservice.dto.response;

public class VehicleTypeResponseDto {
    private Long id;
    private String description;

    public VehicleTypeResponseDto() {}
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private VehicleTypeResponseDto(Builder builder) {
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

        public Builder id(Long id) {
            this.id = id;
            return this;
        }    public Builder description(String description) {
            this.description = description;
            return this;
        }

        public VehicleTypeResponseDto build() {
            return new VehicleTypeResponseDto(this);
        }
    }
}
