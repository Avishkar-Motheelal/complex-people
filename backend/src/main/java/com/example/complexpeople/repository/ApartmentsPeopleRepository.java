package com.example.complexpeople.repository;

import com.example.complexpeople.model.ApartmentsPeople;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApartmentsPeopleRepository extends JpaRepository<ApartmentsPeople, Integer> {

    Optional<ApartmentsPeople> findByApartmentsIdAndPeopleId(int apartmentId, int peopleId);

}
