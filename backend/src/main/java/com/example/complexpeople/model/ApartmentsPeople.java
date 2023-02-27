package com.example.complexpeople.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ApartmentsPeople")
public class ApartmentsPeople {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int apartmentsPeopleId;


    @ManyToOne
    @JoinColumn(name = "PeopleId", referencedColumnName = "PeopleId")
    private Person peopleId;

    @ManyToOne
    @JoinColumn(name = "ApartmentsId", referencedColumnName = "ApartmentsId")
    private Apartment apartmentsId;
}
