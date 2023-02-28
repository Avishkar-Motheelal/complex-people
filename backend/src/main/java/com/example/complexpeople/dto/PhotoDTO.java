package com.example.complexpeople.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {

    @NotNull
    private String photoUrl;
}
