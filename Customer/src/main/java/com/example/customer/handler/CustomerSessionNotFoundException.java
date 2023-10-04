package com.example.customer.handler;

public class CustomerSessionNotFoundException extends RuntimeException{
    public CustomerSessionNotFoundException(String message) {
        super(message);
    }
}
