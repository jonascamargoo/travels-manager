package br.com.jonascamargo.placesmanager.logic.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.PassengerRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.exception.customExceptions.PassengerNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.models.Passenger;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PassengerRepository;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private Slugify slug;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
        this.slug = Slugify.builder().build();
    }
 
    public Passenger createPassenger(PassengerRecordDto passengerRecordDto) {
        Passenger passenger = new Passenger();
        BeanUtils.copyProperties(passengerRecordDto, passenger);
        passenger.setSlug(slug.slugify(passengerRecordDto.name()));
        return passengerRepository.save(passenger);
        
    }

    public List<Passenger> getPassengers() {
        return passengerRepository.findAll();
    } 

    public Passenger getPassengerById(UUID id) {
        return passengerRepository.findById(id).orElseThrow(PassengerNotFoundException::new);
    }

    

}
