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

    @ManyToOne
    @JoinColumn(name = "Complainant", referencedColumnName = "ApartmentsPeopleId")
    private ApartmentsPeople complainant;

    @OneToOne
    @JoinColumn(name = "Respondent", referencedColumnName = "peopleId")
    private People respondent;


    public Complaint(int complaintType, String description, OffsetDateTime date, ApartmentsPeople complainant) {
        this.complaintType = new ComplaintType();
        this.complaintType.setComplaintTypesId(complaintType);

        this.description = description;
        this.date = date;
        this.complainant = complainant;
        this.status  = new Status(2);
    }
}
