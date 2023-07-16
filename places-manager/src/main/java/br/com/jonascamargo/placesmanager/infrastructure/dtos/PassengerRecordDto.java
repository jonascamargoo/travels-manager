package br.com.jonascamargo.placesmanager.infrastructure.dtos;

import jakarta.validation.constraints.NotBlank;

public record PassengerRecordDto(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phoneNumber
    ) {}