package br.com.jonascamargo.travelsmanager.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotNull String login,
        @NotNull String password
    ) {}
