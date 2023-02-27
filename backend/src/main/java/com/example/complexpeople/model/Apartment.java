package com.example.complexpeople.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer apartmentsId;
    private String unitNumber;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ApartmentsPeople",
            joinColumns = @JoinColumn(name = "ApartmentsId"),
            inverseJoinColumns = @JoinColumn(name = "PeopleId"))
    private List<Person> people = new ArrayList<>();

    public Apartment(int apartmentNo) {
        this.apartmentsId = apartmentNo;
    }
}
