package com.example.complexpeople.repository;

import com.example.complexpeople.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    List<Complaint> findAllByOrderByDate();

}
