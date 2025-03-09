package com.example.cabservice.entity;

import com.example.cabservice.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.Date;

public class Payment extends BaseEntity {
    private Long id;
    private Double amount;
    private Date paymentDate;
    private PaymentMethod paymentMethod;
    private Long bookingId;

    public Payment(String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
        super(createdBy, updatedBy, createdDate, updatedDate);
    }
}
