package com.example.complexpeople.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AccessCards")
public class AccessCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID accessCardsId;
    private Boolean activated;
    @ManyToOne()
    @JoinColumn(name = "PeopleId")
    private Person person;
}
