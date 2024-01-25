package br.com.jonascamargo.travelsmanager.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonascamargo.travelsmanager.domain.dtos.PlaceRecordDTO;
import br.com.jonascamargo.travelsmanager.domain.models.Place;
import br.com.jonascamargo.travelsmanager.services.PlaceService;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/")
    public ResponseEntity<Place> createPlace(@RequestBody @Valid PlaceRecordDTO placeRecordDTO) {
        Place createdPlace = placeService.createPlace(placeRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlace);
    }

    @GetMapping("/")
    public ResponseEntity<List<Place>> getPlaces() {
        List<Place> places = placeService.getPlaces();
        return ResponseEntity.status(HttpStatus.OK).body(places);
    }

    @GetMapping("/lista-nome/{name}")
    public ResponseEntity<List<Place>> getPlacesByName(@PathVariable(value = "name") String name) {
        List<Place> places = placeService.getPlacesByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(places);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable(value = "id") UUID id) {
        Place place = placeService.getPlaceById(id);
        place.add(linkTo(methodOn(PlaceController.class).getPlaces()).withRel("Lista de lugares"));
        return ResponseEntity.status(HttpStatus.OK).body(place);
    }

    @GetMapping("/nome/{name}")
    public ResponseEntity<Place> getPlaceByName(@PathVariable(value = "name") String name) {
        Place place = placeService.getPlaceByName(name);
        place.add(linkTo(methodOn(PlaceController.class).getPlaces()).withRel("Lista de lugares"));
        return ResponseEntity.status(HttpStatus.OK).body(place);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Place> updatePlace(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid PlaceRecordDTO placeRecordDTO) {
        Place updatedPlace = placeService.updatePlace(placeRecordDTO, placeService.getPlaceById(id));
        return ResponseEntity.status(HttpStatus.OK).body(updatedPlace);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Place> deleteOnePlace(@PathVariable(value = "id") UUID id) {
        Place place = placeService.deletePlaceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(place);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteAllPlaces() {
        this.placeService.deletePlaces();
        return ResponseEntity.status(HttpStatus.OK).body("All places deleted successfully");
    }

}
