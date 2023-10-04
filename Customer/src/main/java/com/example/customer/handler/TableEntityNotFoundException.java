package com.example.customer.handler;

public class TableEntityNotFoundException extends RuntimeException{
    public TableEntityNotFoundException(String message) {
        super(message);
    }
}
