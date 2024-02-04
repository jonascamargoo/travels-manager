package br.com.jonascamargo.travelsmanager.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthenticationRecordDTO(
        @NotNull String login,
        @NotNull String password
    ) {}
