package com.example.complexpeople.controller;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.dto.NewUserDTO;
import com.example.complexpeople.exception.PersonExistsException;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.model.Provider;
import com.example.complexpeople.model.User;
import com.example.complexpeople.repository.UserRepository;
import com.example.complexpeople.security.SecurityUserDetails;
import com.example.complexpeople.security.jwt.JwtResponse;
import com.example.complexpeople.security.jwt.JwtTokenUtil;
import com.example.complexpeople.service.PeopleService;
import com.example.complexpeople.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PeopleService peopleService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

//    @PostMapping //TODO change return
//    public User register(@RequestBody @Valid NewUserDTO newUserDTO) throws PersonExistsException {
//        NewPersonDTO newPersonDTO = newUserDTO.getPerson();
//        User user = new User();
//        Person person = peopleService.addPerson(newPersonDTO);
//        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
//        user.setEmail(newPersonDTO.getEmailAddress());
//        user.setProvider(Provider.LOCAL);
//        user.setProviderId(null);
//        user.setPerson(person);
//        user.setEnabled(true);
//
//        return userRepository.save(user);
//    }


    @PostMapping
    public ResponseEntity<?> register(@Valid NewUserDTO newUserDTO) {
        User user = new User();
        user.setEmail(newUserDTO.getEmail());

        return new ResponseEntity<>(new JwtResponse(user.getEmail(), jwtTokenUtil.generateToken(new SecurityUserDetails(user))), HttpStatus.CREATED);
    }


    @PostMapping("/person")
    public ResponseEntity<?> addPersonToUser(@Valid NewPersonDTO newPersonDTO, @RequestHeader("Authorization") String token) throws PersonExistsException {
        String email = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUserFromEmail(email);
        if (user.getPerson() != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        Person person = peopleService.addPerson(newPersonDTO);
        user.setPerson(person);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
