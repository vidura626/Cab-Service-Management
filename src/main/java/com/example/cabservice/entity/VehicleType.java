package com.example.cabservice.entity;
import java.sql.Date;
import java.time.LocalDateTime;

public class VehicleType extends BaseEntity{

    private Long id;
    private String description;
    public VehicleType(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
