package com.example.complexpeople.controller;

import com.example.complexpeople.model.User;
import com.example.complexpeople.security.SecurityUserDetails;
import com.example.complexpeople.security.jwt.JwtRequest;
import com.example.complexpeople.security.jwt.JwtResponse;
import com.example.complexpeople.security.jwt.JwtTokenUtil;
import com.example.complexpeople.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
//@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    @Value("${app.googleClientId}")
    private String clientId;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
        throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(userDetails.getUsername(), token));
    }


    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @PostMapping("/login/oauth2/code/google")
    public ResponseEntity<?> googleAuth(@RequestBody String token) throws GeneralSecurityException, IOException {

        String idTokenString = token.replaceAll("\"", "");
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList(clientId))
            .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            User user = userService.processExternalLogin(email);
            UserDetails userDetails = new SecurityUserDetails(user);
            String jwtToken = jwtTokenUtil.generateToken(userDetails);
            if (user.getPerson() == null) {
                return new ResponseEntity<>(new JwtResponse(userDetails.getUsername(), jwtToken), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(new JwtResponse(userDetails.getUsername(), jwtToken), HttpStatus.OK);
        } else {
            System.out.println("Invalid ID token.");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}