package br.com.jonascamargo.placesmanager.infrastructure.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record PaymentRecordDto(
    @NotBlank String description,
    BigDecimal amount,
    LocalDateTime paymentDateTime,
    String paymentMethod,
    String status

) {}