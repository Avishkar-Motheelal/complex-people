package com.example.complexpeople.dto;

import com.example.complexpeople.model.Apartment;
import com.example.complexpeople.model.Person;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link Apartment} entity
 */
@Getter
@Setter
public class NewApartmentDTO {
    private @NotNull String unitNumber;

    public static Apartment toEntity(NewApartmentDTO newApartmentDTO) {
        Apartment apartment = new Apartment();
        apartment.setUnitNumber(newApartmentDTO.getUnitNumber());

        List<Person> people = new ArrayList<>();
        apartment.setPeople(people);

        return apartment;
    }
}
