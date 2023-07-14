package br.com.jonascamargo.placesmanager.api.repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jonascamargo.placesmanager.api.models.Place;

public interface PlaceRepository extends JpaRepository<Place, UUID>{

    Optional<Place> findOneByName(String name);

    List<Place> findListByName(String name);

    @Query("FROM Place p WHERE p.name = :name")
    List<Place> findPlacesFilteredByName(@Param("name") String name);




}




