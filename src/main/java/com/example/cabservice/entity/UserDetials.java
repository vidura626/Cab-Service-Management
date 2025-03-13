package com.example.cabservice.entity;

import java.sql.Date;
import java.time.LocalDateTime;

public class UserDetials extends BaseEntity{
    private Long id;
    private String username;
    private String password;
    private boolean isActive;
    private String role;
    public UserDetials(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
