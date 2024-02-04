package br.com.jonascamargo.travelsmanager.services.logic;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.travelsmanager.dtos.PassengerRecordDTO;
import br.com.jonascamargo.travelsmanager.exceptions.customExceptions.PassengerNotFoundException;
import br.com.jonascamargo.travelsmanager.models.Passenger;
import br.com.jonascamargo.travelsmanager.repositories.PassengerRepository;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private Slugify slug;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
        this.slug = Slugify.builder().build();
    }

    public Passenger createPassenger(PassengerRecordDTO passengerRecordDTO) {
        Passenger passenger = new Passenger();
        BeanUtils.copyProperties(passengerRecordDTO, passenger);
        passenger.setSlug(slug.slugify(passengerRecordDTO.name()));
        return passengerRepository.save(passenger);
    }

    public List<Passenger> getPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(UUID id) {
        return passengerRepository.findById(id).orElseThrow(PassengerNotFoundException::new);
    }

    public Passenger updatePassenger(PassengerRecordDTO passengerRecordDTO, Passenger passenger) {
        BeanUtils.copyProperties(passengerRecordDTO, passenger);
        return passengerRepository.save(passenger);
    }

    public void deletePassenger() {
        passengerRepository.deleteAll();
    }

}
