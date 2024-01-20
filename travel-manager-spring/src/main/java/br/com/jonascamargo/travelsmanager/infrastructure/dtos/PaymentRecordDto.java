package br.com.jonascamargo.travelsmanager.infrastructure.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.jonascamargo.travelsmanager.enums.PaymentMethod;
import br.com.jonascamargo.travelsmanager.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;

public record PaymentRecordDto(
        UUID ticketId,
        UUID passengerId,
        @NotBlank String description,
        BigDecimal amount,
        LocalDateTime paymentTime,
        PaymentStatus status,
        PaymentMethod paymentMethod,
        String cardDigits
    ) {}