package br.com.jonascamargo.placesmanager.infrastructure.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerRecordDto(
        @NotBlank String name,
        @NotNull int age,
        @NotBlank String email,
        @NotBlank String phoneNumber
    ) {}