package com.example.complexpeople.dto;

import com.example.complexpeople.model.ContactDetail;
import com.example.complexpeople.model.IdentificationDocument;
import com.example.complexpeople.model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdatePersonDTO {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    @JsonProperty("identificationDocument")
    private IdentificationDocumentDTO identificationDocumentDTO;

    public static Person toUpdatedEntity(Person person, UpdatePersonDTO updatePersonDTO) {
        person.setFirstName(updatePersonDTO.getFirstName() == null ? person.getFirstName() : updatePersonDTO.getFirstName());
        person.setLastName(updatePersonDTO.getLastName() == null ? person.getLastName() : updatePersonDTO.getLastName());

        // set document stuff
        if (updatePersonDTO.identificationDocumentDTO != null) {
            IdentificationDocument document = IdentificationDocumentDTO.toEntity(updatePersonDTO.getIdentificationDocumentDTO());
            person.setIdentificationDocument(document);
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
}

