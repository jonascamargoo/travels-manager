package br.com.jonascamargo.placesmanager.logic.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.PlaceRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.AssociatedTicketsException;
import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.PlaceNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.models.Place;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PlaceRepository;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final TicketService ticketService;
    private Slugify slug;

    public PlaceService(PlaceRepository placeRepository, TicketService ticketService) {
        this.placeRepository = placeRepository;
        this.ticketService = ticketService;
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
        return placeRepository.findByName(name).orElseThrow(PlaceNotFoundException::new);
    }

    public List<Place> getPlacesByName(String name) {
        return placeRepository.findListByName(name);
    }

    public boolean existsByName(String name) {
        return getPlaceByName(name) != null;
        
    }

    public Place updatePlace(PlaceRecordDto placeRecordDto, Place place) {
        BeanUtils.copyProperties(placeRecordDto, place);
        return placeRepository.save(place);
    }


    public void deletePlaceById(UUID id) {
        Place place = placeRepository.findById(id).orElseThrow(PlaceNotFoundException::new);
        if (!isAvailableToDelete(place.getName())) {
            throw new AssociatedTicketsException("Associated place");
        }
        placeRepository.deleteById(id);

    }

    // analisa se ha algum ticket associado antes de remover
    public boolean isAvailableToDelete(String placeName) {
        return ticketService.hasAssociatedTicket(placeName);
    }


    

}
