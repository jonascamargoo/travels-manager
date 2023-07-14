package br.com.jonascamargo.placesmanager.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.placesmanager.api.dtos.PlaceRecordDto;
import br.com.jonascamargo.placesmanager.api.models.Place;
import br.com.jonascamargo.placesmanager.api.repositories.PlaceRepository;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private Slugify slg;
    //injecao via constructor
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slg = Slugify.builder().build();
    }

    public Place createPlace(PlaceRecordDto placeRecordDto) {
        Place place = new Place();
        BeanUtils.copyProperties(placeRecordDto, place);
        place.setSlug(slg.slugify(placeRecordDto.name()));
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

    public List<Place> getPlacesByName(String name) {
        return placeRepository.findListByName(name);
        
    }

    public Place editPlace(PlaceRecordDto placeRecordDto, Place place) {
        BeanUtils.copyProperties(placeRecordDto, place);
        return placeRepository.save(place);
    }
    
}

