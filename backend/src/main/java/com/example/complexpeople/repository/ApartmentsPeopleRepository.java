package com.example.complexpeople.repository;

import com.example.complexpeople.model.Apartment;
import com.example.complexpeople.model.ApartmentsPeople;
import com.example.complexpeople.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApartmentsPeopleRepository extends JpaRepository<ApartmentsPeople, Integer> {

    Optional<ApartmentsPeople> findByApartmentsIdAndPeopleId(Apartment apartmentsId, Person peopleId);

}
