package com.example.complexpeople.mappers;

import com.example.complexpeople.dto.NewApartmentDTO;
import com.example.complexpeople.model.Apartment;
import com.example.complexpeople.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ApartmentMapper {
    private ApartmentMapper() {
        // utility class
    }

    public static Apartment newApartmentDTOToApartment(NewApartmentDTO newApartmentDTO) {
        Apartment apartment = new Apartment();
        apartment.setUnitNumber(newApartmentDTO.unitNumber());
        List<Person> people = new ArrayList<>();
        apartment.setPeople(people);

        return apartment;
    }
}
