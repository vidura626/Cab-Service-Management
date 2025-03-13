package com.example.cabservice.entity;


import java.sql.Date;
import java.time.LocalDateTime;

public class BaseEntity {
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public BaseEntity(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
