package com.example.complexpeople.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class JwtRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5926468192091050707L;

    private String username;
    private String password;
}