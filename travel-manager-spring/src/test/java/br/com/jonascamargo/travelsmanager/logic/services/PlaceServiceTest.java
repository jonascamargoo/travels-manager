package br.com.jonascamargo.travelsmanager.logic.services;

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

import br.com.jonascamargo.travelsmanager.domain.dtos.PlaceRecordDto;
import br.com.jonascamargo.travelsmanager.domain.models.Place;
import br.com.jonascamargo.travelsmanager.repositories.PlaceRepository;
import br.com.jonascamargo.travelsmanager.services.PlaceService;

@DisplayName("Place Service Tests")
public class PlaceServiceTest {
    @Mock
    PlaceRepository placeRepository;

    @InjectMocks
    PlaceService placeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a place successfully when everything is ok")
    void createPlace() {
        // Arrange
        PlaceRecordDto placeRecordDto = new PlaceRecordDto("Test Place", null);
        // Mocking behavior
        when(placeRepository.save(any())).thenReturn(new Place());
        // Act
        Place createdPlace = placeService.createPlace(placeRecordDto);
        // Assert
        assertNotNull(createdPlace);
        verify(placeRepository, times(1)).save(any());

    }


}


    