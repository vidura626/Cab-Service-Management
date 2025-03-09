package com.example.cabservice.entity;

import java.sql.Date;

public class Audit extends BaseEntity{
    public Audit(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
