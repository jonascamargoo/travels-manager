package br.com.jonascamargo.placesmanager.logic.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.PassengerRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.models.Passenger;

import br.com.jonascamargo.placesmanager.infrastructure.repositories.PassengerRepository;

@DisplayName("Passanger Service Tests")
public class PassengerServiceTest {
    @Mock
    PassengerRepository passengerRepository;

    @InjectMocks
    PassengerService passengerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    @DisplayName("Should create a passanger successfully when everything is ok")
    void createPassenger() {
        PassengerRecordDto validReq = new PassengerRecordDto(
                "joao",
                20,
                "joao@gmail.com",
                "4002-8922");
        when(passengerRepository.save(any())).thenReturn(new Passenger());
        Passenger creatPassenger = passengerService.createPassenger(validReq);
        assertNotNull(creatPassenger);
        verify(passengerRepository, times(1)).save(any());
    }

}