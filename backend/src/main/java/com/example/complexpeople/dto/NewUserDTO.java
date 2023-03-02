package com.example.complexpeople.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class NewUserDTO {
    @Email
    @NotNull
    private String email;
    @NotNull
    @Length(min = 6)
    private String password;
}
