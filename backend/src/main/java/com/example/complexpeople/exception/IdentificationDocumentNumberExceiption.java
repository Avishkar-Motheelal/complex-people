package com.example.complexpeople.exception;

public class IdentificationDocumentNumberExceiption extends Exception {
    public IdentificationDocumentNumberExceiption() {
        super("Either ID number OR passport number must be provided");
    }
}
