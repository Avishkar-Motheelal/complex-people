package com.example.complexpeople.exception;

public class ApartmentNotFoundException extends Exception {
    public ApartmentNotFoundException() {
        super("No apartment found for that id");
    }
}
