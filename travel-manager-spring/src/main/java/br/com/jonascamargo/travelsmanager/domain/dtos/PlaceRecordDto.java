package br.com.jonascamargo.travelsmanager.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record PlaceRecordDto(
        @NotBlank String name,
        String slug
        
    ) {}
