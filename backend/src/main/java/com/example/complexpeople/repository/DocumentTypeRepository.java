package com.example.complexpeople.repository;

import com.example.complexpeople.model.DocumentType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DocumentTypeRepository extends CrudRepository<DocumentType, Integer> {

    Optional<DocumentType> findByTypeIgnoreCase(String roleName);
}