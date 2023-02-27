package com.example.complexpeople.dto;

import com.example.complexpeople.model.DocumentType;
import com.example.complexpeople.model.IdentificationDocument;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentificationDocumentDTO {

    @NotNull
    private String number;

    @Pattern(regexp = "^(id|passport)$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Document type can be either 'ID' or 'PASSPORT'")
    @NotNull
    private String documentType;

    public static IdentificationDocument toEntity(IdentificationDocumentDTO documentDTO) {
        DocumentType type = new DocumentType();
        type.setType(documentDTO.getDocumentType());

        IdentificationDocument document = new IdentificationDocument();
        document.setDocumentType(type);
        document.setNumber(documentDTO.getNumber());

        return document;
    }

}
