package br.com.jonascamargo.travelsmanager.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonascamargo.travelsmanager.models.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, UUID>{
    Optional<Passenger> findOneByName(String name);
}