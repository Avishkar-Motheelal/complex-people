package com.example.complexpeople.controller;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.dto.NewUserDTO;
import com.example.complexpeople.exception.IdentificationDocumentNumberExceiption;
import com.example.complexpeople.exception.PersonExistsException;
import com.example.complexpeople.mappers.PersonMapper;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.model.Provider;
import com.example.complexpeople.model.User;
import com.example.complexpeople.repository.PeopleRepository;
import com.example.complexpeople.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final PeopleRepository peopleRepo;
    private final PasswordEncoder passwordEncoder;


    @PostMapping
    public User register(@RequestBody @Valid NewUserDTO newUserDTO) throws IdentificationDocumentNumberExceiption, PersonExistsException {
        NewPersonDTO newPersonDTO = newUserDTO.getPerson();
        User user = new User();
        if (newPersonDTO.getIdNumber() == null && newPersonDTO.getPassportNumber() == null) {
            throw new IdentificationDocumentNumberExceiption();
        }

        String newPersonDTOIdentificationNumber = newPersonDTO.getIdNumber() == null ? newPersonDTO.getPassportNumber() : newPersonDTO.getIdNumber();
        if (peopleRepo.existsByIdentificationDocumentNumber(newPersonDTOIdentificationNumber)) {
            throw new PersonExistsException();
        }

        Person person = PersonMapper.mapNewPersonDTOtoPerson(newPersonDTO);

        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        user.setEmail(newPersonDTO.getEmailAddress());
        user.setProvider(Provider.LOCAL);
        user.setProviderId(null);
        user.setPerson(person);
        user.setEnabled(true);

        return userRepository.save(user);
    }
}
