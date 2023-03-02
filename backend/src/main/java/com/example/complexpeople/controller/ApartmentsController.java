package com.example.complexpeople.controller;

import com.example.complexpeople.dto.NewApartmentDTO;
import com.example.complexpeople.exception.*;
import com.example.complexpeople.model.Apartment;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.repository.ApartmentsRepository;
import com.example.complexpeople.repository.PeopleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public Apartment getApartment(@PathVariable Integer id) throws ApartmentNotFoundException {
        return apartmentsRepo.findById(id).orElseThrow(ApartmentNotFoundException::new);
    }

    @PostMapping
    public Apartment addApartment(@RequestBody @Valid NewApartmentDTO newApartmentDTO) throws ApartmentExistsException {
        // make sure apartment doesn't already exist
        if (apartmentsRepo.existsByUnitNumberIgnoreCase(newApartmentDTO.getUnitNumber())) {
            throw new ApartmentExistsException();
        }

        Apartment apartment = NewApartmentDTO.toEntity(newApartmentDTO);

        return apartmentsRepo.save(apartment);
    }

    @GetMapping("{id}/people")
    public Apartment getPeopleInSpecificApartment(@PathVariable Integer id) throws ApartmentNotFoundException {
        return apartmentsRepo.findById(id).orElseThrow(ApartmentNotFoundException::new);
    }

    @PostMapping("/{apartmentId}/people/{personId}")
    public Apartment addExistingPersonToApartment(@PathVariable Integer apartmentId, @PathVariable Integer personId) throws PersonNotFoundException, ApartmentNotFoundException, PersonExistsException {
        Person person = peopleRepo.findById(personId).orElseThrow(PersonNotFoundException::new);
        Apartment apartment = apartmentsRepo.findById(apartmentId).orElseThrow(ApartmentNotFoundException::new);

        List<Person> people = apartment.getPeople();

        boolean alreadyLivesInApartment = people.stream().anyMatch(v -> v.getPeopleId().equals(personId));
        if (alreadyLivesInApartment) {
            throw new PersonExistsException();
        }

        people.add(person);
        apartment.setPeople(people);

        return apartmentsRepo.save(apartment);

    }

    @DeleteMapping("/{apartmentId}/people/{personId}")
    public Apartment removePersonFromApartment(@PathVariable Integer apartmentId, @PathVariable Integer personId) throws PersonNotFoundException, ApartmentNotFoundException, PersonNotAssociatedException {
        Person person = peopleRepo.findById(personId).orElseThrow(PersonNotFoundException::new);
        Apartment apartment = apartmentsRepo.findById(apartmentId).orElseThrow(ApartmentNotFoundException::new);

        List<Person> people = apartment.getPeople();

        boolean alreadyLivesInApartment = people.stream().anyMatch(v -> v.getPeopleId().equals(personId));
        if (!alreadyLivesInApartment) {
            throw new PersonNotAssociatedException();
        }

        people.remove(person);
        apartment.setPeople(people);

        return apartmentsRepo.save(apartment);
    }
}
