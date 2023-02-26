package com.example.complexpeople.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdatePersonDTO {

    private String firstName;

    private String lastName;

    private String idNumber;

    private String passportNumber;

    private String phoneNumber;

    private String emailAddress;
}

