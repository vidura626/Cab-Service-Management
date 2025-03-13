package com.example.cabservice.util;

import com.example.cabservice.exceptions.ValidationException;

/**
 * Generic Validator interface for validating different types of entities.
 *
 * @param <T> The type of entity to validate.
 */
public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
