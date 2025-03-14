package com.example.cabservice.util.validator_impl;

import com.example.cabservice.exceptions.ValidationException;
import com.example.cabservice.util.Validator;

/**
 * Validator for Vehicle Type description.
 */
public class VehicleTypeValidator implements Validator<String> {

    @Override
    public void validate(String description) throws ValidationException {
        if (description == null || description.isEmpty()) {
            throw new ValidationException("Vehicle type description is required.");
        }
    }
}
