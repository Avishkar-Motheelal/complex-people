package com.example.complexpeople.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class JwtResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8091872930283046844L;
    private final String jwttoken;
}