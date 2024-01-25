package br.com.jonascamargo.travelsmanager.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record PlaceRecordDTO(
        @NotBlank String name,
        String slug
    ) {}
