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
@Table(name = "Complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer complaintsId;
    @ManyToOne
    @JoinColumn(name = "complaintTypesId")
    private ComplaintType complaintType;
    private String description;
    private OffsetDateTime date;
    @ManyToOne
    @JoinColumn(name = "statusId")
    private Status status;
    private Integer complainant;
    private Integer respondent;
}
