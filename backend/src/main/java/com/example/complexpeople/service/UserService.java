package com.example.complexpeople.service;

import com.example.complexpeople.exception.UserExistsException;
import com.example.complexpeople.exception.ValidationException;
import com.example.complexpeople.model.User;
import com.example.complexpeople.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User processExternalLogin(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.orElseGet(() -> createUser(email));
    }


    private User createUser(String email) {
        String password = "hyuiahrulghagh89347y982ig"; //TODO generate random password
        return createUser(email, password);
    }


    public User createUser(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new UserExistsException("User already exists with that email");
        }
        User user = new User();
        user.setEnabled(true);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }


    public User getUserFromEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User was not found"));
    }
}
