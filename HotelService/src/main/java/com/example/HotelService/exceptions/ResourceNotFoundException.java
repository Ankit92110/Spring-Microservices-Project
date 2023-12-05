package com.example.HotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found exception");
    }
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
