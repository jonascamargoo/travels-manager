package br.com.jonascamargo.placesmanager.logic.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.PassengerRecordDto;
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
    

}
