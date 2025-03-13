package com.example.cabservice.entity;

import java.time.LocalDateTime;

public class VehicleType extends BaseEntity {
    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private VehicleType(Builder builder) {
        super(builder.createdBy, builder.updatedBy, builder.createdDate, builder.updatedDate);
        this.id = builder.id;
        this.description = builder.description;
    }
    public static class Builder {
        private Long id;
        private String description;
        private String createdBy;
        private String updatedBy;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
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

        public VehicleType build() {
            return new VehicleType(this);
        }
    }
}
