package br.com.jonascamargo.travelsmanager.logic.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.travelsmanager.infrastructure.dtos.PassengerRecordDto;
import br.com.jonascamargo.travelsmanager.infrastructure.exceptions.customExceptions.PassengerNotFoundException;
import br.com.jonascamargo.travelsmanager.infrastructure.models.Passenger;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.PassengerRepository;

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

    public Passenger updatePassenger(PassengerRecordDto passengerRecordDto, Passenger passenger) {
        BeanUtils.copyProperties(passengerRecordDto, passenger);
        return passengerRepository.save(passenger);
    }

    public void deletePassenger() {
        passengerRepository.deleteAll();
    }

}