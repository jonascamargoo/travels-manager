package br.com.jonascamargo.placesmanager.logic.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.PlaceRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.exception.customExceptions.PlaceNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.models.Place;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PlaceRepository;

//Todos os casos do Optional estarem vazios serao tratados na class de excecoes que sera criada junto aos teste

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private Slugify slug;

    //injecao via constructor
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slug = Slugify.builder().build();
        
    }

    public Place createPlace(PlaceRecordDto placeRecordDto) {
        Place place = new Place();
        BeanUtils.copyProperties(placeRecordDto, place);
        place.setSlug(slug.slugify(placeRecordDto.name()));
        return placeRepository.save(place);
    }

    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    public Place getPlaceById(UUID id) {
        return placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);   
    }

    public Place getPlaceByName(String name) {
        return placeRepository.findOneByName(name).orElseThrow(PlaceNotFoundException::new);
    }

    public List<Place> getPlacesByName(String name) {
        return placeRepository.findListByName(name);
    }

    public Place updatePlace(PlaceRecordDto placeRecordDto, Place place) {
        BeanUtils.copyProperties(placeRecordDto, place);
        return placeRepository.save(place);
    }

    // public void deletePlaceById(UUID id) {
    //     Optional<Place> place = placeRepository.findById(id);
    //     if(!isAvailableToDelete(place.get())) {
    //         throw new IllegalArgumentException("Deletion failed. There is one or more tickets associated with this place.");
    //     }
    //     placeRepository.deleteById(id);
    // }

    // public boolean isAvailableToDelete(Place place) {
    //     return place.getTicketList().isEmpty();
    // }
    
}

