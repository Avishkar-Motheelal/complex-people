package com.example.complexpeople.service;

import com.example.complexpeople.dto.NewPersonDTO;
import com.example.complexpeople.exception.PersonExistsException;
import com.example.complexpeople.exception.VisitNotFoundException;
import com.example.complexpeople.model.Apartment;
import com.example.complexpeople.model.Person;
import com.example.complexpeople.model.Photo;
import com.example.complexpeople.model.Visit;
import com.example.complexpeople.repository.ApartmentsRepository;
import com.example.complexpeople.repository.PhotoRepository;
import com.example.complexpeople.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;

    private final PeopleService peopleService;

    private final PhotoRepository photoRepository;

    private final ApartmentsRepository apartmentsRepository;

    public Iterable<Visit> getAll() {
        return visitRepository.findAll();
    }

    public Visit getVisitById(int id) throws VisitNotFoundException {
        return visitRepository.findById(id).orElseThrow(VisitNotFoundException::new);}


    public Visit updateVisit(int id) throws VisitNotFoundException{
        Visit visit;

        if(visitRepository.findById(id).isPresent()){
            visit = visitRepository.findById(id).get();
        }
        else{
             throw new VisitNotFoundException();
        }
        visit.setDateOut(OffsetDateTime.now());
        return visitRepository.save(visit);
    }

    public Visit addVisit(NewPersonDTO personDTO, int apartmentId, int photoId){

        Person person;

        try{
            person = peopleService.addPerson(personDTO);

        }catch(PersonExistsException e){
            Iterable<Person> personIterable = peopleService.findAll();

            person = findPerson(personIterable, personDTO);


        }

        Apartment apartment = apartmentsRepository.getApartmentByApartmentsId(apartmentId);
        Photo photo = photoRepository.getPhotoByPhotosId(photoId);

        Visit visit = new Visit();
        visit.setVisitor(person);
        visit.setPhoto(photo);
        visit.setApartment(apartment);
        visit.setDateIn(OffsetDateTime.now());

        return visitRepository.save(visit);
    }

    private Person findPerson(Iterable<Person> people, NewPersonDTO personDTO){
        for(Person person: people){
            if(Objects.equals(person.getIdentificationDocument().getNumber(), personDTO.getIdentificationDocumentDTO().getNumber())){

                return person;
            }

        }
        return null;

    }



}
