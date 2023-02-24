package com.example.complexpeople.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "People")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer peopleId;
    private String firstName;
    private String lastName;
    @OneToOne
    @Column(name = "IdentificationDocumentsId")
    private IdentificationDocument identificationDocument;
    @OneToOne
    @Column(name = "contactDetailsId")
    private ContactDetail contactDetail;
}
