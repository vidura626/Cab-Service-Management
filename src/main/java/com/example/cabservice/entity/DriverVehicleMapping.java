package com.example.cabservice.entity;

import java.sql.Date;

public class DriverVehicleMapping extends BaseEntity{
    private Long id;
    private Long driverId;
    private Long vehicleTypeId;

    public DriverVehicleMapping(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
