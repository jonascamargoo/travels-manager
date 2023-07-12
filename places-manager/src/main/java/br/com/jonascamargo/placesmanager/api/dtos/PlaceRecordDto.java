package br.com.jonascamargo.placesmanager.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record PlaceRecordDto(
        @NotBlank String name,
        @NotBlank String slug,
        @NotBlank String city,
        @NotBlank String state
    ) {}

