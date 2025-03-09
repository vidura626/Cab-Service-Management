package com.example.cabservice.entity;

import java.sql.Date;

public class Customer extends BaseEntity{

    public Customer(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
