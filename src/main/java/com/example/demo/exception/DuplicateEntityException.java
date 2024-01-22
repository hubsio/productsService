package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEntityException extends ProductException {
    public DuplicateEntityException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
