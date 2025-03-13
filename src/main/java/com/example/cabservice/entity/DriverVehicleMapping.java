package com.example.cabservice.entity;

import java.sql.Date;
import java.time.LocalDateTime;

public class DriverVehicleMapping extends BaseEntity{
    private Long id;
    private Long driverId;
    private Long vehicleTypeId;

    public DriverVehicleMapping(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
