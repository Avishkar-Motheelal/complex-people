package com.example.complexpeople.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "IdentificationDocuments")
public class IdentificationDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificationDocumentsId;

    private String number;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "DocumentTypesId")
    private DocumentType documentType;
}
