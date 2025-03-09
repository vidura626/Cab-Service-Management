package com.example.cabservice.entity;

import java.sql.Date;

public class Notification extends BaseEntity{
    public Notification(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
