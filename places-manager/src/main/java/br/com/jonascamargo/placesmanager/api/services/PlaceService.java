package br.com.jonascamargo.placesmanager.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import br.com.jonascamargo.placesmanager.api.dtos.PlaceRecordDto;
import br.com.jonascamargo.placesmanager.api.models.Place;
import br.com.jonascamargo.placesmanager.api.repositories.PlaceRepository;

public class PlaceService {
    private PlaceRepository placeRepository;

    //injecao via constructor
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place createPlace(PlaceRecordDto placeRecordDto) {
        Place place = new Place();
        BeanUtils.copyProperties(placeRecordDto, place);
        return placeRepository.save(place);
    }

    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceById(UUID id) {
        return placeRepository.findById(id);
    }

    public Optional<Place> getPlaceByName(String name) {
        return placeRepository.findOneByName(name);
    }

    public List<Place> getPlacesFilteredByName(String name) {
        return placeRepository.findListByName(name);
        
    }

    public Place editPlace(PlaceRecordDto placeRecordDto, Place place) {
        BeanUtils.copyProperties(placeRecordDto, place);
        return placeRepository.save(place);
    }
    
}

