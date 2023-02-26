package com.example.complexpeople.exception;

public class RoleNotAssignedException extends Exception {
    public RoleNotAssignedException() {
        super("Person does not have that role");
    }
}
