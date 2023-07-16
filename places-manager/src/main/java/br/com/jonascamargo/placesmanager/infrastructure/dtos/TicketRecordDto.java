package br.com.jonascamargo.placesmanager.infrastructure.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//coloco aqui um campo Place place; ?

public record TicketRecordDto(
        @NotBlank String passengerName,
        @NotBlank String source,
        @NotBlank String destination,
        @NotNull BigDecimal price,
        LocalDateTime departureTime,
        LocalDateTime purchaseTime
        
    ) {}



