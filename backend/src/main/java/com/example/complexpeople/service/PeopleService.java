package com.example.complexpeople.service;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.dto.RoleDTO;
import com.example.complexpeople.dto.UpdatePersonDTO;
import com.example.complexpeople.exception.*;
import com.example.complexpeople.mappers.PersonMapper;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.model.Role;
import com.example.complexpeople.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepo;


    public Iterable<Person> findAll() {
        return peopleRepo.findAll();
    }


    public Person findById(Integer id) throws PersonNotFoundException {
        return peopleRepo.findById(id).orElseThrow(PersonNotFoundException::new);
    }


    public Person addPerson(NewPersonDTO newPersonDTO) throws IdentificationDocumentNumberException, PersonExistsException {
        if (identificationNumberMissing(newPersonDTO.getIdNumber(), newPersonDTO.getPassportNumber())) {
            throw new IdentificationDocumentNumberException();
        }

        String newPersonDTOIdentificationNumber = newPersonDTO.getIdNumber() == null ? newPersonDTO.getPassportNumber() : newPersonDTO.getIdNumber();
        if (peopleRepo.existsByIdentificationDocumentNumber(newPersonDTOIdentificationNumber)) {
            throw new PersonExistsException();
        }

        Person person = PersonMapper.mapNewPersonDTOtoPerson(newPersonDTO);
        return peopleRepo.save(person);
    }


    public Person updatePerson(Integer id, UpdatePersonDTO updatePersonDTO) throws PersonNotFoundException, IdentificationDocumentNumberException {
        Person person = peopleRepo.findById(id).orElseThrow(PersonNotFoundException::new);

        if (identificationNumberMissing(updatePersonDTO.getIdNumber(), updatePersonDTO.getPassportNumber())) {
            throw new IdentificationDocumentNumberException();
        }

        Person updatedPerson = PersonMapper.mapUpdatePersonDTOToPerson(updatePersonDTO, person);
        return peopleRepo.save(updatedPerson);
    }


    public Person addRolesToPerson(Integer id, RoleDTO roleDTO) throws PersonNotFoundException, RoleAlreadyAssignedException {
        Person person = peopleRepo.findById(id).orElseThrow(PersonNotFoundException::new);

        if (personAlreadyHasRole(person, roleDTO.getRoleType())) {
            throw new RoleAlreadyAssignedException();
        }

        List<Role> roles = person.getRoles();
        Role role = PersonMapper.mapRoleDTOToRole(roleDTO);
        roles.add(role);

        person.setRoles(roles);
        return peopleRepo.save(person);
    }


    private boolean identificationNumberMissing(String idNumber, String passportNumber) {
        return idNumber == null && passportNumber == null;
    }


    private boolean personAlreadyHasRole(Person person, String roleToHave) {
        return person.getRoles().stream().anyMatch(role -> role.getType().equalsIgnoreCase(roleToHave));
    }


    public Person deleteRoleFromPerson(Integer id, RoleDTO roleDTO) throws PersonNotFoundException, RoleNotAssignedException {
        Person person = peopleRepo.findById(id).orElseThrow(PersonNotFoundException::new);

        if (!personAlreadyHasRole(person, roleDTO.getRoleType())) {
            throw new RoleNotAssignedException();
        }

        List<Role> roles = person.getRoles();
        roles.removeIf(role -> role.getType().equalsIgnoreCase(roleDTO.getRoleType()));
        person.setRoles(roles);

        return peopleRepo.save(person);
    }
}
