package br.com.jonascamargo.placesmanager.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.jonascamargo.placesmanager.infrastructure.models.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, UUID>{
    Optional<Passenger> findOneByName(String name);
    List<Passenger> findListByName(String name);
    List<Passenger> findPasengersFilteredByName(@Param("name") String name);

}