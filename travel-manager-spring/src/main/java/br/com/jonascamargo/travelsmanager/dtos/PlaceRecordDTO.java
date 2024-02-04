package br.com.jonascamargo.travelsmanager.dtos;

import jakarta.validation.constraints.NotBlank;

public record PlaceRecordDTO(
        @NotBlank String name,
        String slug
    ) {}
