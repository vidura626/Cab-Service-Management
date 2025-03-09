package com.example.cabservice.entity;

import java.sql.Date;

public class Booking extends BaseEntity {
    public Booking(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
