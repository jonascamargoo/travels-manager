package br.com.jonascamargo.travelsmanager.dtos;

import br.com.jonascamargo.travelsmanager.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterRecordDTO(
        @NotNull String login,
        @NotNull String password,
        @NotNull UserRole userRole
    ) {}