package com.example.complexpeople.repository;

import com.example.complexpeople.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<Person, Integer> {
    boolean existsByIdentificationDocumentNumber(String number);
}
