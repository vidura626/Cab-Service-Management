package com.example.cabservice.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class Customer extends BaseEntity{
    private long id;
    private String name;
    private String address;
    private String nic;
    private String email;
    private String mobileNumber;

    public Customer(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
