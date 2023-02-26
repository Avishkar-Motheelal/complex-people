package com.example.complexpeople.exception;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException() {
        super("Person Not Found");
    }
}
