package com.example.complexpeople.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visitsId;
    @OneToOne
    @Column(name = "visitorId")
    private Person visitor;
    @OneToOne
    @Column(name = "apartmentsId")
    private Apartment apartment;
    private OffsetDateTime dateIn;
    private OffsetDateTime dateOut;
    @OneToOne
    @Column(name = "photosId")
    private Photo photo;
}
