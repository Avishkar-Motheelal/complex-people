package com.example.complexpeople.dto;

import com.example.complexpeople.model.ContactDetail;
import com.example.complexpeople.model.IdentificationDocument;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NewPersonDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @JsonProperty("identificationDocument")
    private IdentificationDocumentDTO identificationDocumentDTO;

    @NotNull
    @NotBlank
    private String phoneNumber;

    @NotNull
    @Email
    @NotBlank
    private String emailAddress;

    @NotNull
    @JsonProperty("role")
    private RoleDTO roleDTO;

    public static Person toEntity(NewPersonDTO newPersonDTO) {
        IdentificationDocument document = IdentificationDocumentDTO.toEntity(newPersonDTO.getIdentificationDocumentDTO());

        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setPhoneNumber(newPersonDTO.getPhoneNumber());
        contactDetail.setEmailAddress(newPersonDTO.getEmailAddress());

        List<Role> roles = new ArrayList<>();
        roles.add(RoleDTO.toEntity(newPersonDTO.getRoleDTO()));

        Person person = new Person();
        person.setFirstName(newPersonDTO.getFirstName());
        person.setLastName(newPersonDTO.getLastName());
        person.setIdentificationDocument(document);
        person.setContactDetail(contactDetail);
        person.setRoles(roles);

        return person;
    }

}
