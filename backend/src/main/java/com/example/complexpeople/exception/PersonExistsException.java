package com.example.complexpeople.exception;

public class PersonExistsException extends Exception {
    public PersonExistsException() {
        super("Person already exists");
    }
}
