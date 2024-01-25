package br.com.jonascamargo.travelsmanager.domain.dtos;

import br.com.jonascamargo.travelsmanager.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotNull String login,
        @NotNull String password,
        @NotNull UserRole userRole
    ) {}