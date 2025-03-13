package com.example.cabservice.util.validator_impl;

import com.example.cabservice.dto.request.DriverRequestDto;
import com.example.cabservice.exceptions.ValidationException;
import com.example.cabservice.util.Validator;

/**
 * Validator for DriverRequestDto.
 */
public class DriverValidator implements Validator<DriverRequestDto> {

    @Override
    public void validate(DriverRequestDto driver) throws ValidationException {
        if (driver.getName() == null || driver.getName().isEmpty())
            throw new ValidationException("Driver name is required.");
        if (driver.getNic() == null || driver.getNic().isEmpty())
            throw new ValidationException("NIC is required.");
        if (driver.getLicence() == null || driver.getLicence().isEmpty())
            throw new ValidationException("License is required.");
        if (driver.getStatus() == null)
            throw new ValidationException("Driver status must be specified.");
    }
}
