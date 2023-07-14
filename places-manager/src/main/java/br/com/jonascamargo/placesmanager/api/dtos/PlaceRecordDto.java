package br.com.jonascamargo.placesmanager.api.dtos;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;


//faltam lidar com os horarios de createAt e updatedAt
public record PlaceRecordDto(
        @NotBlank String name,
        // @NotBlank String city,
        //@NotBlank String state,
        String slug,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {}
