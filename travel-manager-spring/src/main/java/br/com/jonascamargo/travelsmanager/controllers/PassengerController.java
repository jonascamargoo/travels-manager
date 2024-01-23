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

import br.com.jonascamargo.travelsmanager.infrastructure.dtos.PassengerRecordDto;
import br.com.jonascamargo.travelsmanager.infrastructure.models.Passenger;
import br.com.jonascamargo.travelsmanager.logic.services.PassengerService;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/")
    public ResponseEntity<Passenger> createPassenger(@RequestBody @Valid PassengerRecordDto passengerRecordDto) {
        Passenger createdPassenger = passengerService.createPassenger(passengerRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPassenger);
    }

    @GetMapping("/")
    public ResponseEntity<List<Passenger>> getPassengers() {
        List<Passenger> passengers = passengerService.getPassengers();
        return ResponseEntity.status(HttpStatus.OK).body(passengers);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPassengerById(@PathVariable(value = "id") UUID id) {
        Passenger passenger = passengerService.getPassengerById(id);
        passenger.add(linkTo(methodOn(PassengerController.class).getPassengers()).withRel("Lista de passageiros"));
        return ResponseEntity.status(HttpStatus.OK).body(passenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePassenger(
        @PathVariable(value = "id") UUID id,
        @RequestBody @Valid PassengerRecordDto passengerRecordDto) {
        Passenger updatedPassenger = passengerService.updatePassenger(passengerRecordDto, passengerService.getPassengerById(id));
        return ResponseEntity.status(HttpStatus.OK).body(updatedPassenger);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteAllPassengers() {
        this.passengerService.deletePassenger();
        return ResponseEntity.status(HttpStatus.OK).body("All passengers deleted successfully.");
    }

}
