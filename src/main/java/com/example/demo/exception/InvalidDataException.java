package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class InvalidDataException extends ProductException {
    public InvalidDataException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
