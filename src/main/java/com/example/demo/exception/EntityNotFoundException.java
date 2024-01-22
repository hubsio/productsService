package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ProductException {
    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
