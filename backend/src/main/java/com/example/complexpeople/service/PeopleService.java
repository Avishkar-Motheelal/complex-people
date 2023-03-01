package com.example.complexpeople.service;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.dto.RoleDTO;
import com.example.complexpeople.dto.UpdatePersonDTO;
import com.example.complexpeople.exception.PersonExistsException;
import com.example.complexpeople.exception.PersonNotFoundException;
import com.example.complexpeople.exception.RoleAlreadyAssignedException;
import com.example.complexpeople.exception.RoleNotAssignedException;
import com.example.complexpeople.model.DocumentType;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.model.Role;
import com.example.complexpeople.repository.DocumentTypeRepository;
import com.example.complexpeople.repository.PeopleRepository;
import com.example.complexpeople.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepo;
    private final RoleRepository roleRepo;
    private final DocumentTypeRepository documentTypeRepository;


    public Iterable<Person> findAll() {
        return peopleRepo.findAll();
    }


    public Person findById(Integer id) throws PersonNotFoundException {
        return peopleRepo.findById(id).orElseThrow(PersonNotFoundException::new);
    }


    public Person addPerson(NewPersonDTO newPersonDTO) throws PersonExistsException {

        if (peopleRepo.existsByIdentificationDocumentNumber(newPersonDTO.getIdentificationDocumentDTO().getNumber())) {
            throw new PersonExistsException();
        }

        Person person = NewPersonDTO.toEntity(newPersonDTO);
        updatePersonDocumentTypeId(person);
        updatePersonRoleId(person);

        return peopleRepo.save(person);
    }


    private void updatePersonRoleId(Person person) {
        List<Role> roles = person.getRoles();

        List<Role> updatedRoles = new ArrayList<>();
        for (Role role : roles) {
            if (role.getRolesId() == null) {
                role = roleRepo.findByTypeIgnoreCase(role.getType()).orElseThrow(RuntimeException::new);
            }
            updatedRoles.add(role);
        }

        person.setRoles(updatedRoles);
    }


    private void updatePersonDocumentTypeId(Person person) {
        DocumentType personDocumentType = person.getIdentificationDocument().getDocumentType();

        Integer repoId = documentTypeRepository.
            findByTypeIgnoreCase(personDocumentType.getType()).
            orElseThrow().
            getDocumentTypesId();

        personDocumentType.setDocumentTypesId(repoId);
    }


    public Person updatePerson(Integer id, UpdatePersonDTO updatePersonDTO) throws PersonNotFoundException, PersonExistsException {
        Person person = peopleRepo.findById(id).orElseThrow(PersonNotFoundException::new);

        Person updatedPerson = UpdatePersonDTO.toUpdatedEntity(person, updatePersonDTO);
        updatePersonDocumentTypeId(person);

        // make sure id given doesn't actually exist yet :0
        String originalIdNumber = person.getIdentificationDocument().getNumber();
        String updateIdNumber = updatedPerson.getIdentificationDocument().getNumber();

        if (!Objects.equals(originalIdNumber, updateIdNumber) && peopleRepo.existsByIdentificationDocumentNumber(updateIdNumber)) {
            throw new PersonExistsException();
        }

        return peopleRepo.save(updatedPerson);
    }


    public Person addRolesToPerson(Integer id, RoleDTO roleDTO) throws PersonNotFoundException, RoleAlreadyAssignedException {
        Person person = peopleRepo.findById(id).orElseThrow(PersonNotFoundException::new);

        if (personAlreadyHasRole(person, roleDTO.getRoleType())) {
            throw new RoleAlreadyAssignedException();
        }

        List<Role> roles = person.getRoles();
        Role role = RoleDTO.toEntity(roleDTO);
        role.setRolesId(roleRepo.findByTypeIgnoreCase(role.getType()).orElseThrow(RuntimeException::new).getRolesId());

        roles.add(role);

        person.setRoles(roles);
        return peopleRepo.save(person);
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
