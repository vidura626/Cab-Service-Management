package com.example.cabservice.entity;

import java.sql.Date;

public class UserDetials extends BaseEntity{
    private Long id;
    private String username;
    private String password;
    private boolean isActive;
    private String role;
    public UserDetials(String createdBy, String updatedBy, Date createdDate, Date updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
