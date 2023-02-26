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
    @JoinColumn(name = "visitorId")
    private Person visitor;
    @OneToOne
    @JoinColumn(name = "apartmentsId")
    private Apartment apartment;
    private OffsetDateTime dateIn;
    private OffsetDateTime dateOut;
    @OneToOne
    @JoinColumn(name = "photosId")
    private Photo photo;
}
