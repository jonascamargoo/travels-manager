package br.com.jonascamargo.placesmanager.infrastructure.dtos;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;


public record PlaceRecordDto(
        @NotBlank String name,
        @NotBlank String city,
        @NotBlank String state,
        String slug,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {}
