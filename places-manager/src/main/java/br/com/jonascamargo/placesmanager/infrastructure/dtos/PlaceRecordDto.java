package br.com.jonascamargo.placesmanager.infrastructure.dtos;

import jakarta.validation.constraints.NotBlank;

public record PlaceRecordDto(
        @NotBlank String name,
        String slug
        
    ) {}
