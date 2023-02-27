package com.example.complexpeople.exception;

public class ComplaintNotFoundException extends Exception{

    public ComplaintNotFoundException() {
        super("Complaint not found, please check if the complaintId provided is correct");
    }
}
