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
    @Column(name = "accessCardsId", columnDefinition = "uuid")
    private UUID accessCardId;
    private boolean activated;
    @ManyToOne()
    @JoinColumn(name = "PeopleId")
    private Person person;


    public AccessCard(Person person, boolean activated) {
        this.person = person;
        this.activated = activated;
    }
}
