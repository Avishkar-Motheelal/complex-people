package com.example.complexpeople.dto;

import com.example.complexpeople.model.Person;
import lombok.Data;

@Data
public class UpdateUserPersonDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String type;
    private String number;
    private String role;


    public NewPersonDTO toNewPersonDTO() {
        NewPersonDTO newPersonDTO = new NewPersonDTO();
        newPersonDTO.setFirstName(firstName);
        newPersonDTO.setLastName(lastName);
        newPersonDTO.setPhoneNumber(phoneNumber);
        newPersonDTO.setEmailAddress(email);
        IdentificationDocumentDTO identificationDocumentDTO = new IdentificationDocumentDTO();
        identificationDocumentDTO.setDocumentType(type);
        identificationDocumentDTO.setNumber(number);
        newPersonDTO.setIdentificationDocumentDTO(identificationDocumentDTO);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleType(role);
        newPersonDTO.setRoleDTO(roleDTO);
        return newPersonDTO;
    }
}
