package br.com.jonascamargo.placesmanager.infrastructure.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.jonascamargo.placesmanager.infrastructure.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;

public record PaymentRecordDto(
        UUID ticketId,
        UUID passengerId,
        @NotBlank String description,
        BigDecimal amount,
        LocalDateTime paymentTime,
        PaymentStatus status
    ) {}