package com.example.cabservice.entity;
import java.sql.Date;

public class VehicleType extends BaseEntity{

    private Long id;
    private String description;
    public VehicleType(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
