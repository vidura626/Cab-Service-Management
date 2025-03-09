package com.example.cabservice.entity;

import java.sql.Date;

public class BookingDetails extends BaseEntity {
    public BookingDetails(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
