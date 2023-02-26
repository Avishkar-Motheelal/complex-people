package com.example.complexpeople.dto;

import jakarta.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.example.complexpeople.model.Apartment} entity
 */
public record NewApartmentDTO(@NotNull String unitNumber) {
}
