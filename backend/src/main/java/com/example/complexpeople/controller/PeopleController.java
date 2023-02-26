package com.example.complexpeople.controller;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.dto.RoleDTO;
import com.example.complexpeople.dto.UpdatePersonDTO;
import com.example.complexpeople.exception.*;
import com.example.complexpeople.mappers.PersonMapper;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.model.Role;
import com.example.complexpeople.repository.PeopleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleRepository peopleRepo;

    @GetMapping
    public Iterable<Person> getPeople() {
        return peopleRepo.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Integer id) throws PersonNotFoundException {
        Optional<Person> optionalPerson = peopleRepo.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException();
        }
        return optionalPerson.get();
    }

    @PostMapping
    public Person addPerson(@Valid @RequestBody NewPersonDTO newPersonDTO) throws IdentificationDocumentNumberExceiption, PersonExistsException {
        if (newPersonDTO.getIdNumber() == null && newPersonDTO.getPassportNumber() == null) {
            throw new IdentificationDocumentNumberExceiption();
        }

        String newPersonDTOIdentificationNumber = newPersonDTO.getIdNumber() == null ? newPersonDTO.getPassportNumber() : newPersonDTO.getIdNumber();
        if (peopleRepo.existsByIdentificationDocumentNumber(newPersonDTOIdentificationNumber)) {
            throw new PersonExistsException();
        }

        Person person = PersonMapper.mapNewPersonDTOtoPerson(newPersonDTO);
        return peopleRepo.save(person);
    }


    @PatchMapping("/{id}")
    public Person updatePerson(@PathVariable Integer id, @RequestBody UpdatePersonDTO updatePersonDTO) throws PersonNotFoundException, IdentificationDocumentNumberExceiption {
        Optional<Person> optionalPerson = peopleRepo.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException();
        }

        if (updatePersonDTO.getIdNumber() != null && updatePersonDTO.getPassportNumber() != null) {
            throw new IdentificationDocumentNumberExceiption();
        }

        Person updatedPerson = PersonMapper.mapUpdatePersonDTOToPerson(updatePersonDTO, optionalPerson.get());
        return peopleRepo.save(updatedPerson);
    }

    @PostMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRolesToPerson(@PathVariable Integer id, @RequestBody RoleDTO roleDTO) throws PersonNotFoundException, RoleAlreadyAssignedException {
        Optional<Person> optionalPerson = peopleRepo.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException();
        }

        Person person = optionalPerson.get();
        // make sure role doesn't already exist
        boolean exists = person.getRoles().stream().anyMatch(role -> role.getType().equalsIgnoreCase(roleDTO.getRoleType()));
        if (exists) {
            throw new RoleAlreadyAssignedException();
        }

        List<Role> roles = person.getRoles();
        Role role = PersonMapper.mapRoleDTOToRole(roleDTO);
        roles.add(role);

        person.setRoles(roles);
        peopleRepo.save(person);
    }

    @DeleteMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoleFromPerson(@PathVariable Integer id, @RequestBody RoleDTO roleDTO) throws PersonNotFoundException, RoleNotAssignedException {
        Optional<Person> optionalPerson = peopleRepo.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException();
        }

        Person person = optionalPerson.get();

        // make sure role exists
        boolean exists = person.getRoles().stream().anyMatch(role -> role.getType().equalsIgnoreCase(roleDTO.getRoleType()));
        if (!exists) {
            throw new RoleNotAssignedException();
        }

        List<Role> roles = person.getRoles();
        roles.removeIf(role -> role.getType().equalsIgnoreCase(roleDTO.getRoleType()));

        person.setRoles(roles);
        peopleRepo.save(person);
    }
}
