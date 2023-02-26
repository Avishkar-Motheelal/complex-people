package com.example.complexpeople.exception;

public class ApartmentExistsException extends Exception {
    public ApartmentExistsException() {
        super("Apartment with that unit number already exists.");
    }
}
