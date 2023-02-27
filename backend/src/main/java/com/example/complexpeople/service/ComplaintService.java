package com.example.complexpeople.service;

import com.example.complexpeople.dto.ComplaintDTO;
import com.example.complexpeople.exception.NotFoundException;
import com.example.complexpeople.model.ApartmentsPeople;
import com.example.complexpeople.model.Complaint;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.model.Status;
import com.example.complexpeople.repository.ApartmentsPeopleRepository;
import com.example.complexpeople.repository.ComplaintRepository;
import com.example.complexpeople.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    ComplaintRepository complaintRepository;
    ApartmentsPeopleRepository apartmentsPeopleRepository;
    PeopleRepository peopleRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository,
                            ApartmentsPeopleRepository apartmentsPeopleRepository,
                            PeopleRepository peopleRepository) {
        this.complaintRepository = complaintRepository;
        this.apartmentsPeopleRepository = apartmentsPeopleRepository;
        this.peopleRepository = peopleRepository;
    }

    public Complaint getSpecificComplaint(int id) {

        Optional<Complaint> byId = complaintRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        }

        throw new NotFoundException("Complaint with id: %s not found.".formatted(id));

    }

    public Complaint removeComplaint(int id) {
        Optional<Complaint> complaint = complaintRepository.findById(id);
        if (complaint.isPresent()) {
            Complaint complaint1 = complaint.get();
            complaint1.setStatus(new Status(4));
            return complaintRepository.save(complaint1);
        }

        throw new NotFoundException("Can't delete nonexistent complaint, please check if the id provided is correct");
    }

    public Complaint updateComplaint(int id, int status, int staffId, String desc) {

        Optional<Complaint> complaint = complaintRepository.findById(id);

        if (complaint.isPresent()) {
            Complaint complaint1 = complaint.get();

            if (status != 0) {
                complaint1.setStatus(new Status(status));
            }

            if (staffId != 0) {
                Optional<Person> people = peopleRepository.findById(staffId);
                if (people.isPresent()) {
                    complaint1.setRespondent(people.get());
                } else {
                    throw new NotFoundException("User with id: %s not found, check if id is correct".formatted(staffId));
                }
            }

            if (desc != null && complaint.get().getStatus().getStatusId() != 1) {
                complaint1.setDescription(desc);
            } else {
                throw new NotFoundException("Can't change status of complaint marked as complete.");
            }

            return complaintRepository.save(complaint1);
        }

        throw new NotFoundException("Complaint with id: %s was not found".formatted(id));
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAllByOrderByDate();
    }

    public Complaint createComplaint(ComplaintDTO complaintDTO) {

        Optional<ApartmentsPeople> apartmentsPeople = apartmentsPeopleRepository
                .findByApartmentsIdAndPeopleId(complaintDTO.apartmentNo(), complaintDTO.personId());

        if (apartmentsPeople.isPresent()) {
            ZoneOffset zoneOffSet = ZoneOffset.of("+02:00");
            OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffSet);

            return complaintRepository.save(new Complaint(complaintDTO.complaintType(), complaintDTO.description(), offsetDateTime, apartmentsPeople.get()));
        }

        throw new NotFoundException("Apartment with id: %s was not found".formatted(complaintDTO.apartmentNo()));

    }


}
