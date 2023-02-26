package com.example.complexpeople.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
public class RolesPeople {
    @Id
    private int rolesPeopleId;

    @JoinColumn(name = "peopleId")
    private int peopleId;
    @JoinColumn(name = "rolesId")
    private int rolesId;
}
