package com.example.cabservice.entity;

import com.example.cabservice.enums.BookingStatus;

import java.sql.Date;
import java.time.LocalDateTime;

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
    private BookingStatus status;
    public Booking(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
