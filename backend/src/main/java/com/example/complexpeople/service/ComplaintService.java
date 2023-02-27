package com.example.complexpeople.service;

import com.example.complexpeople.dto.ComplaintDTO;
import com.example.complexpeople.exception.ApartmentNotFoundException;
import com.example.complexpeople.exception.ComplaintChangeException;
import com.example.complexpeople.exception.ComplaintNotFoundException;
import com.example.complexpeople.exception.PersonNotFoundException;
import com.example.complexpeople.model.*;
import com.example.complexpeople.repository.ApartmentsPeopleRepository;
import com.example.complexpeople.repository.ComplaintRepository;
import com.example.complexpeople.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ApartmentsPeopleRepository apartmentsPeopleRepository;
    private final PeopleRepository peopleRepository;

    public Complaint getSpecificComplaint(int id) throws ComplaintNotFoundException {

        return complaintRepository.findById(id).orElseThrow(ComplaintNotFoundException::new);

    }

    public Complaint removeComplaint(int id) throws ComplaintChangeException {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(ComplaintChangeException::new);

        complaint.setStatus(new Status(4));
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(int id, int status, int staffId, String desc)
            throws ApartmentNotFoundException, PersonNotFoundException, ComplaintChangeException {

        Complaint complaint = complaintRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);

        if (status != 0) {
            complaint.setStatus(new Status(status));
        }

        if (staffId != 0 && checkStatus(complaint.getStatus().getStatusId())) {
            Person people = peopleRepository.findById(staffId).orElseThrow(PersonNotFoundException::new);
            complaint.setRespondent(people);
        }

        if (desc != null && checkStatus(complaint.getStatus().getStatusId())) {
            complaint.setDescription(desc);
        } else {
            throw new ComplaintChangeException();
        }

        return complaintRepository.save(complaint);
    }

    private boolean checkStatus(int id) {
        return id != 1;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAllByOrderByDate();
    }

    public Complaint createComplaint(ComplaintDTO complaintDTO) throws ApartmentNotFoundException {

        ApartmentsPeople apartmentsPeople = apartmentsPeopleRepository
                .findByApartmentsIdAndPeopleId(new Apartment(complaintDTO.apartmentNo()), new Person(complaintDTO.personId()))
                .orElseThrow(ApartmentNotFoundException::new);

        OffsetDateTime offsetDateTime = OffsetDateTime.parse(OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        return complaintRepository.save(new Complaint(complaintDTO.complaintType(),
                complaintDTO.description(),
                offsetDateTime, apartmentsPeople));

    }


}
