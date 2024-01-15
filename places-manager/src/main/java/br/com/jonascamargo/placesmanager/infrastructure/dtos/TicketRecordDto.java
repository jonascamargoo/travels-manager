package br.com.jonascamargo.placesmanager.infrastructure.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;


public record TicketRecordDto(
        @NotNull String source,
        @NotNull String destination,
        @NotNull BigDecimal price,
        LocalDateTime departureTime,
        LocalDateTime purchaseTime
        
    ) {}



