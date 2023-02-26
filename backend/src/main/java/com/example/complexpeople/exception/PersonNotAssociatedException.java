package com.example.complexpeople.exception;

public class PersonNotAssociatedException extends Exception {
    public PersonNotAssociatedException() {
        super("The person is not associated with the apartment");
    }
}
