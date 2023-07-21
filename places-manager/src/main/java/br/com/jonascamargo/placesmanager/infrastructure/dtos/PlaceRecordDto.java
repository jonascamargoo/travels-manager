package br.com.jonascamargo.placesmanager.infrastructure.dtos;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

public record PlaceRecordDto(
        @NotBlank String name,
        @NotBlank String city,
        @NotBlank String state,
        String slug,
        LocalDateTime createdAt, //preciso desse createdAt e updateAt aqui no DTO? talvez sim para poder utilizar do DTO para fazer as verificacoes e nao precisar instanciar para isso. Talvez nao, pois ambos nao sao fornecidos pelo client e sim gerados automaticamente
        LocalDateTime updatedAt
    ) {}
