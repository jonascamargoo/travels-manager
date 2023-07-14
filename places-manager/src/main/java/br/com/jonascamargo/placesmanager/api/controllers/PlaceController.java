package br.com.jonascamargo.placesmanager.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonascamargo.placesmanager.api.dtos.PlaceRecordDto;
import br.com.jonascamargo.placesmanager.api.models.Place;
import br.com.jonascamargo.placesmanager.api.services.PlaceService;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PlaceController {
    
    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/lugar")
    public ResponseEntity<Place> createPlace(@RequestBody @Valid PlaceRecordDto placeRecordDto) {
        Place createdPlace = placeService.createPlace(placeRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlace);
    }

    @GetMapping("/lugar")
    public ResponseEntity<List<Place>> getPlaces() {
      List<Place> places = placeService.getPlaces();
      return ResponseEntity.status(HttpStatus.OK).body(places);
    }


    @GetMapping("lugar/lista-nome/{nome}")
    public ResponseEntity<List<Place>> getPlacesFilteredByName(String name) {
        List<Place> listPlaces = placeService.getPlacesFilteredByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(listPlaces);
    }


    @GetMapping("/lugar/{id}")
    public ResponseEntity<Object> getPlaceById(@PathVariable(value = "id") UUID id) {
        Optional<Place> placeO = placeService.getPlaceById(id);
        if(placeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lugar não encontrado");
        }
        placeO.get().add(linkTo(methodOn(PlaceController.class).getPlaces()).withRel("Lista de lugares"));
        return ResponseEntity.status(HttpStatus.OK).body(placeO.get());
    }

    @GetMapping("/lugar/nome/{nome}")
    public ResponseEntity<Object> getPlaceByName(String name) {
        Optional<Place> placeO = placeService.getPlaceByName(name);
        if(placeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lugar não encontrado para o nome: " + name);
        }
        placeO.get().add(linkTo(methodOn(PlaceController.class).getPlaces()).withRel("Lista de lugares"));
        return ResponseEntity.status(HttpStatus.OK).body(placeO);
    }

    @PutMapping("/lugar/{id}")
    public ResponseEntity<Object> editPlace(
        @PathVariable(value = "id") UUID id,
        @RequestBody @Valid PlaceRecordDto placeRecordDto
    ) {
        Optional<Place> placeO = placeService.getPlaceById(id);
        if(placeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lugar não encontrado para o id: " + id);
        }
        Place editedPlace = placeService.editPlace(placeRecordDto, placeO.get());
        return ResponseEntity.status(HttpStatus.OK).body(editedPlace);
    }
    

}
