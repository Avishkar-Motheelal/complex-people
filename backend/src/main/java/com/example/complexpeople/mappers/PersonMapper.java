package com.example.complexpeople.mappers;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.dto.RoleDTO;
import com.example.complexpeople.dto.UpdatePersonDTO;
import com.example.complexpeople.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonMapper {

    private PersonMapper() {
        // utility class
    }

    public static Person mapNewPersonDTOtoPerson(NewPersonDTO newPersonDTO) {
        // create document
        IdentificationDocument document = createDocument(newPersonDTO.getIdNumber(), newPersonDTO.getPassportNumber());

        // set contact for person
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setPhoneNumber(newPersonDTO.getPhoneNumber());
        contactDetail.setEmailAddress(newPersonDTO.getEmailAddress());

        // add role
        List<Role> roles = new ArrayList<>();
        roles.add(createRole(newPersonDTO.getRoleType()));

        // create person
        Person person = new Person();
        person.setFirstName(newPersonDTO.getFirstName());
        person.setLastName(newPersonDTO.getLastName());
        person.setIdentificationDocument(document);
        person.setContactDetail(contactDetail);
        person.setRoles(roles);

        return person;
    }

    public static Person mapUpdatePersonDTOToPerson(UpdatePersonDTO updatePersonDTO, Person person) {
        person.setFirstName(updatePersonDTO.getFirstName() == null ? person.getFirstName() : updatePersonDTO.getFirstName());
        person.setLastName(updatePersonDTO.getLastName() == null ? person.getLastName() : updatePersonDTO.getLastName());

        if (updatePersonDTO.getIdNumber() != null || updatePersonDTO.getPassportNumber() != null) {
            IdentificationDocument updatedDocument = createDocument(updatePersonDTO.getIdNumber(), updatePersonDTO.getPassportNumber());
            updatedDocument.setIdentificationDocumentsId(person.getIdentificationDocument().getIdentificationDocumentsId());
            person.setIdentificationDocument(updatedDocument);
        }

        if (updatePersonDTO.getPhoneNumber() != null || updatePersonDTO.getEmailAddress() != null) {
            ContactDetail contactDetail = new ContactDetail();
            contactDetail.setPhoneNumber(updatePersonDTO.getPhoneNumber() == null
                    ? person.getContactDetail().getPhoneNumber()
                    : updatePersonDTO.getPhoneNumber());
            contactDetail.setEmailAddress(updatePersonDTO.getEmailAddress() == null
                    ? person.getContactDetail().getEmailAddress()
                    : updatePersonDTO.getEmailAddress());

            contactDetail.setContactDetailsId(person.getContactDetail().getContactDetailsId());
            person.setContactDetail(contactDetail);
        }

        return person;
    }

    public static Role mapRoleDTOToRole(RoleDTO roleDTO) {
        return createRole(roleDTO.getRoleType());
    }

    private static IdentificationDocument createDocument(String idNumber, String passportNumber) {
        DocumentType type = new DocumentType();
        type.setType(idNumber == null ? "PASSPORT" : "ID");
        type.setDocumentTypesId(idNumber == null ? 2 : 1);

        IdentificationDocument document = new IdentificationDocument();
        document.setNumber(type.getType().equals("ID") ? idNumber : passportNumber);
        document.setDocumentType(type);
        return document;
    }

    private static Role createRole(String roleType) {
        Role role = new Role();
        role.setType(roleType.toLowerCase(Locale.ROOT));

        // role id
        switch (role.getType()) {
            case "staff" -> role.setRolesId(1);
            case "resident" -> role.setRolesId(2);
            default -> role.setRolesId(3);
        }

        return role;
    }


}
