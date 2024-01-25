package br.com.jonascamargo.travelsmanager.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record TicketRecordDTO(
        @NotNull String source,
        @NotNull String destination,
        @NotNull BigDecimal price,
        LocalDateTime departureTime,
        LocalDateTime purchaseTime
        
    ) {}



