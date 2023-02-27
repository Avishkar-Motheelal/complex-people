package com.example.complexpeople.exception;

public class ComplaintChangeException extends Exception{

    public ComplaintChangeException() {
        super("Can't modify complain/maintenance issue marked that is marked as complete.");
    }
}
