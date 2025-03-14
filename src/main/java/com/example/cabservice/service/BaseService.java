package com.example.cabservice.service;
public abstract class BaseService {
    protected void executeWithExceptionHandling(Runnable action) {
        try {
            action.run();
        } catch (Exception e) {
            throw new RuntimeException("Service error: " + e.getMessage(), e);
        }
    }
}