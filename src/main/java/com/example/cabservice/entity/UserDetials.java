package com.example.cabservice.entity;

import java.sql.Date;

public class UserDetials extends BaseEntity{
    public UserDetials(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
