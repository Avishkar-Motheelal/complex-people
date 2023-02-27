package com.example.complexpeople.controller;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.dto.RoleDTO;
import com.example.complexpeople.dto.UpdatePersonDTO;
import com.example.complexpeople.exception.*;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.service.PeopleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    @GetMapping
    public Iterable<Person> getPeople() {
        return peopleService.findAll();
    }


    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Integer id) throws PersonNotFoundException {
        return peopleService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@Valid @RequestBody NewPersonDTO newPersonDTO) throws IdentificationDocumentNumberException, PersonExistsException {
        return peopleService.addPerson(newPersonDTO);

    }


    @PatchMapping("/{id}")
    public Person updatePerson(@PathVariable Integer id, @RequestBody UpdatePersonDTO updatePersonDTO) throws PersonNotFoundException, IdentificationDocumentNumberException {
        return peopleService.updatePerson(id, updatePersonDTO);
    }


    @PostMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Person addRolesToPerson(@PathVariable Integer id, @RequestBody RoleDTO roleDTO) throws PersonNotFoundException, RoleAlreadyAssignedException {
        return peopleService.addRolesToPerson(id, roleDTO);
    }


    @DeleteMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.OK)
    public Person deleteRoleFromPerson(@PathVariable Integer id, @RequestBody RoleDTO roleDTO) throws PersonNotFoundException, RoleNotAssignedException {
        return peopleService.deleteRoleFromPerson(id, roleDTO);
    }
}
