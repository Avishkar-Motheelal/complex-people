package com.example.complexpeople.service;

import com.example.complexpeople.model.Provider;
import com.example.complexpeople.model.User;
import com.example.complexpeople.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public User processExternalLogin(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.orElseGet(() -> createUser(email));
    }


    private User createUser(String email) {
        User user = new User();
        user.setEnabled(true);
        user.setProvider(Provider.GOOGLE);
        user.setEmail(email);

        return user;
    }


    public User getUserFromEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User was not found"));
    }
}
