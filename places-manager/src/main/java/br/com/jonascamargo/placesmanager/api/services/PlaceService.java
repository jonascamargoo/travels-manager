package br.com.jonascamargo.placesmanager.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import br.com.jonascamargo.placesmanager.api.dtos.PlaceRecordDto;
import br.com.jonascamargo.placesmanager.api.models.Place;
import br.com.jonascamargo.placesmanager.api.repositories.PlaceRepository;

public class PlaceService { //FALTA CRIAR A A SOBRECARGA DE LISTPLACES FILTRANDO PELO NOME
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

    //Melhorar esse método. Será que realmente preciso obter todos para retornar alguns ou nenhum?
    public List<Place> getPlacesFilteredByName(String name) {
        List<Place> places = placeRepository.findAll();

        return places.stream()
            .filter(place -> place.getName().equals(name))
            .collect(Collectors.toList());
        
    }


    public Optional<Place> getPlaceById(UUID id) {
        return placeRepository.findById(id);
    }

    // public Optional<Place> getPlaceByName(String name) {
    //     // return placeRepository.findBy
    //     return placeRepository.findByName(name);
    // }

    public Place editPlace(PlaceRecordDto placeRecordDto, Place place) {
        BeanUtils.copyProperties(placeRecordDto, place);
        return placeRepository.save(place);
    }





    
}

