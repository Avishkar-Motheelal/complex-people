package com.example.complexpeople.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPersonDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String idNumber;

    private String passportNumber;

    @NotNull
    @NotBlank
    private String phoneNumber;

    @NotNull
    @Email
    @NotBlank
    private String emailAddress;

    @Pattern(regexp = "^(staff|resident|visitor)$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "User type must be 'staff', 'resident' or 'visitor'")
    @Schema(description = "The type of user", allowableValues = {"staff", "resident", "visitor"})
    private String roleType;

}
