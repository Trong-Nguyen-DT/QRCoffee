package com.example.customer.handler;

public class CustomerEntityNotFoundException extends RuntimeException {
    public CustomerEntityNotFoundException(String message) {
        super(message);
    }
}
