package com.example.complexpeople.exception;

public class PersonExistsException extends Exception {
    public PersonExistsException() {
        super("Person with that identification document number already exists");
    }
}
