package com.example.cabservice.entity;

import com.example.cabservice.enums.PaymentMethod;

import java.util.Date;

public class Payment {
    private Long id;
    private Double amount;
    private Date paymentDate;
    private PaymentMethod paymentMethod;
    private Long bookingId;

}
