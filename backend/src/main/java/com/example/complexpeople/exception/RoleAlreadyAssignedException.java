package com.example.complexpeople.exception;

public class RoleAlreadyAssignedException extends Exception {
    public RoleAlreadyAssignedException() {
        super("Person already has that role.");
    }
}
