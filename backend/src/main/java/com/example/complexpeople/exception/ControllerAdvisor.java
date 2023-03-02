package com.example.complexpeople.exception;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {
    @ApiResponse(responseCode = "404")
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionBody> handleNotFoundException(NotFoundException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ApiResponse(responseCode = "404")
    @ExceptionHandler(ComplaintNotFoundException.class)
    public ResponseEntity<ExceptionBody> handleComplaintNotFoundException(ComplaintNotFoundException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ApiResponse(responseCode = "403")
    @ExceptionHandler(ComplaintChangeException.class)
    public ResponseEntity<ExceptionBody> handleComplaintChangeException(ComplaintChangeException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionBody> handleValidationException(ValidationException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ExceptionBody> handleUserExistsException(UserExistsException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ExceptionBody>> handleBindException(BindException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        List<ExceptionBody> exceptions = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .map(message -> new ExceptionBody(dateTime, message))
            .toList();
        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }


    @ApiResponse(responseCode = "404")
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(PersonExistsException.class)
    public ResponseEntity<Object> handleIdentificationExists(PersonExistsException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(RoleAlreadyAssignedException.class)
    public ResponseEntity<Object> handleRoleExistsException(RoleAlreadyAssignedException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(RoleNotAssignedException.class)
    public ResponseEntity<Object> handleRoleNotAssigned(RoleNotAssignedException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ApiResponse(responseCode = "400")
    @ExceptionHandler(ApartmentExistsException.class)
    public ResponseEntity<Object> handleApartmentExists(ApartmentExistsException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ApiResponse(responseCode = "404")
    @ExceptionHandler(ApartmentNotFoundException.class)
    public ResponseEntity<Object> handleApartmentNotFound(ApartmentNotFoundException exception) {
        String dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ExceptionBody body = new ExceptionBody(dateTime, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
