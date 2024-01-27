package br.com.jonascamargo.travelsmanager.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthenticationRecordDTO(
        @NotNull String login,
        @NotNull String password
    ) {}
