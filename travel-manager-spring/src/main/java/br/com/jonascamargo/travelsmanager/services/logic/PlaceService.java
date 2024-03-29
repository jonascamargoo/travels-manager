package br.com.jonascamargo.travelsmanager.services.logic;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.travelsmanager.dtos.PlaceRecordDTO;
import br.com.jonascamargo.travelsmanager.exceptions.customExceptions.AssociatedTicketsException;
import br.com.jonascamargo.travelsmanager.exceptions.customExceptions.PlaceNotFoundException;
import br.com.jonascamargo.travelsmanager.models.Place;
import br.com.jonascamargo.travelsmanager.repositories.PlaceRepository;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private Slugify slug;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slug = Slugify.builder().build();

    }

    public Place createPlace(PlaceRecordDTO placeRecordDTO) {
        Place place = new Place();
        BeanUtils.copyProperties(placeRecordDTO, place);
        place.setSlug(slug.slugify(placeRecordDTO.name()));
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

    public Place updatePlace(PlaceRecordDTO placeRecordDTO, Place place) {
        BeanUtils.copyProperties(placeRecordDTO, place);
        return placeRepository.save(place);
    }

    public Place deletePlaceById(UUID id) {
        try {
            Place place = getPlaceById(id);
            placeRepository.deleteById(id);
            return place;
        } catch (EmptyResultDataAccessException e) {
            throw new PlaceNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new AssociatedTicketsException();
        }
    }

    public void deletePlaces() {
        placeRepository.deleteAll();
    }

}
