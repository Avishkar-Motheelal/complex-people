package com.example.complexpeople.repository;

import com.example.complexpeople.model.Apartment;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentsRepository extends CrudRepository<Apartment, Integer> {
    boolean existsByUnitNumberIgnoreCase(String unitNumber);

    Apartment getApartmentByApartmentsId(int id);
}
