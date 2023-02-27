package com.example.complexpeople.dto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class NewUserDTO {
    @Valid
    private NewPersonDTO person;
    private String password;
}
