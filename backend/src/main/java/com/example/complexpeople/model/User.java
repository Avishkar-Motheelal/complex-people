package com.example.complexpeople.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PeopleId")
    private Person person;
    private String password;
    private boolean enabled;


    @Transient
    public List<Role> getRoles() {
        if (person == null) {
            Role role = new Role();
            role.setType("VISITOR");
            return Collections.singletonList(role);
        }
        return person.getRoles();
    }
}