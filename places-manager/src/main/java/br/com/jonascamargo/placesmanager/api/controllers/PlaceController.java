package br.com.jonascamargo.placesmanager.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.PlaceRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.models.Place;
import br.com.jonascamargo.placesmanager.logic.services.PlaceService;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/lugares")
    public ResponseEntity<Place> createPlace(@RequestBody @Valid PlaceRecordDto placeRecordDto) {
        Place createdPlace = placeService.createPlace(placeRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlace);
    }

    @GetMapping("/lugares")
    public ResponseEntity<List<Place>> getPlaces() {
        List<Place> places = placeService.getPlaces();
        return ResponseEntity.status(HttpStatus.OK).body(places);
    }

    @GetMapping("lugares/lista-nome/{name}")
    public ResponseEntity<List<Place>> getPlacesByName(@PathVariable(value = "name") String name) {
        List<Place> places = placeService.getPlacesByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(places);
    }

    @GetMapping("/lugares/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable(value = "id") UUID id) {
        Place place = placeService.getPlaceById(id);
        place.add(linkTo(methodOn(PlaceController.class).getPlaces()).withRel("Lista de lugares"));
        return ResponseEntity.status(HttpStatus.OK).body(place);
    }

    @GetMapping("/lugares/nome/{name}")
    public ResponseEntity<Object> getPlaceByName(@PathVariable(value = "name") String name) {
        Place place = placeService.getPlaceByName(name);
        place.add(linkTo(methodOn(PlaceController.class).getPlaces()).withRel("Lista de lugares"));
        return ResponseEntity.status(HttpStatus.OK).body(place);
    }

    @PutMapping("/lugares/{id}")
    public ResponseEntity<Object> updatePlace(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid PlaceRecordDto placeRecordDto) {
        Place updatedPlace = placeService.updatePlace(placeRecordDto, placeService.getPlaceById(id));
        return ResponseEntity.status(HttpStatus.OK).body(updatedPlace);
    }

    // @DeleteMapping("/lugares/{id}")
    // public ResponseEntity<Object> deletePlaceById(@PathVariable(value = "id") UUID id) {
        
    // }
    
    

    // @DeleteMapping("/lugares/{id}")
    // public ResponseEntity<Object> deletePlaceById(
    // @PathVariable(value = "id") UUID id) {
    // Optional<Place> place = placeService.getPlaceById(id);
    // if (place.isEmpty())
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    // return ResponseEntity.status(HttpStatus.OK).body(place.get() + "Product
    // deleted successfully.");
    // }

}
