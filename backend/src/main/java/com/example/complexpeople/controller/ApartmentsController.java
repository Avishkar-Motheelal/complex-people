package com.example.complexpeople.controller;

import com.example.complexpeople.dto.NewApartmentDTO;
import com.example.complexpeople.exception.*;
import com.example.complexpeople.mappers.ApartmentMapper;
import com.example.complexpeople.model.Apartment;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.repository.ApartmentsRepository;
import com.example.complexpeople.repository.PeopleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
public class ApartmentsController {

    private final ApartmentsRepository apartmentsRepo;
    private final PeopleRepository peopleRepo;

    @GetMapping
    public Iterable<Apartment> getAll() {
        return apartmentsRepo.findAll();
    }

    @PostMapping
    public Apartment addApartment(@RequestBody @Valid NewApartmentDTO newApartmentDTO) throws ApartmentExistsException {
        // make sure apartment doesn't already exist
        if (apartmentsRepo.existsByUnitNumberIgnoreCase(newApartmentDTO.unitNumber())) {
            throw new ApartmentExistsException();
        }
        Apartment apartment = ApartmentMapper.newApartmentDTOToApartment(newApartmentDTO);

        return apartmentsRepo.save(apartment);
    }

    @GetMapping("{id}/people")
    public Apartment getPeopleInSpecificApartment(@PathVariable Integer id) throws ApartmentNotFoundException {
        Optional<Apartment> optionalApartment = apartmentsRepo.findById(id);
        if (optionalApartment.isEmpty()) {
            throw new ApartmentNotFoundException();
        }
        return optionalApartment.get();
    }

    @PostMapping("/{apartmentId}/people/{personId}")
    public Apartment addExistingPersonToApartment(@PathVariable Integer apartmentId, @PathVariable Integer personId) throws PersonNotFoundException, ApartmentNotFoundException, PersonExistsException {
        // make sure person exists
        Optional<Person> optionalPerson = peopleRepo.findById(personId);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException();
        }

        // get the person
        Person person = optionalPerson.get();

        Optional<Apartment> optionalApartment = apartmentsRepo.findById(apartmentId);
        if (optionalApartment.isEmpty()) {
            throw new ApartmentNotFoundException();
        }

        Apartment apartment = optionalApartment.get();
        List<Person> people = apartment.getPeople();

        boolean exists = people.stream().anyMatch(v -> v.getPeopleId().equals(personId));
        if (exists) {
            throw new PersonExistsException();
        }

        people.add(person);
        apartment.setPeople(people);

        return apartmentsRepo.save(apartment);

    }

    @DeleteMapping("/{apartmentId}/people/{personId}")
    public Apartment removePersonFromApartment(@PathVariable Integer apartmentId, @PathVariable Integer personId) throws PersonNotFoundException, ApartmentNotFoundException, PersonNotAssociatedException {
        Optional<Person> optionalPerson = peopleRepo.findById(personId);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException();
        }

        Person person = optionalPerson.get();

        Optional<Apartment> optionalApartment = apartmentsRepo.findById(apartmentId);
        if (optionalApartment.isEmpty()) {
            throw new ApartmentNotFoundException();
        }

        Apartment apartment = optionalApartment.get();
        List<Person> people = apartment.getPeople();

        boolean exists = people.stream().anyMatch(v -> v.getPeopleId().equals(personId));
        if (!exists) {
            throw new PersonNotAssociatedException();
        }

        people.remove(person);
        apartment.setPeople(people);

        return apartmentsRepo.save(apartment);
    }
}
