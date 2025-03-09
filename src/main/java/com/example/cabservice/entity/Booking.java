package com.example.cabservice.entity;

import java.sql.Date;

public class Booking extends BaseEntity {
    private Long id;
    private Long customerId;
    private Long driverId;
    private Long vehicleId;
    private Double pickUpLocationLat;
    private Double pickUpLocationLng;
    private Double dropLocationLat;
    private Double dropLocationLng;
    private Double distance;
    public Booking(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
