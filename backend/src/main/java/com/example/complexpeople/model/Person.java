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
@Table(name = "People")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer peopleId;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "IdentificationDocumentsId")
    private IdentificationDocument identificationDocument;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "contactDetailsId")
    private ContactDetail contactDetail;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "RolesPeople",
            joinColumns = @JoinColumn(name = "peopleId"),
            inverseJoinColumns = @JoinColumn(name = "rolesId"))
    private List<Role> roles = new ArrayList<>();
}
