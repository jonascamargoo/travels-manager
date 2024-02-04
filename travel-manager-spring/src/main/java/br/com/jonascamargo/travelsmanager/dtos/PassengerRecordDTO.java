package br.com.jonascamargo.travelsmanager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerRecordDTO(
        @NotBlank String name,
        @NotNull int age,
        @NotBlank String email,
        @NotBlank String phoneNumber
    ) {}