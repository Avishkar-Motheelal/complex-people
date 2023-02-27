package com.example.complexpeople.exception;

public class IdentificationDocumentNumberException extends Exception {
    public IdentificationDocumentNumberException() {
        super("Either ID number OR passport number must be provided");
    }
}
