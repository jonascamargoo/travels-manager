package br.com.jonascamargo.placesmanager.api.repositories;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonascamargo.placesmanager.api.models.Place;


public interface PlaceRepository extends JpaRepository<Place, UUID>{
    //Optional<Place> findByName(String name);
}




