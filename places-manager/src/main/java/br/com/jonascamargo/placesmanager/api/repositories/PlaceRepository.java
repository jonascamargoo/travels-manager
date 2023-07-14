package br.com.jonascamargo.placesmanager.api.repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jonascamargo.placesmanager.api.models.Place;

public interface PlaceRepository extends JpaRepository<Place, UUID>{

    Optional<Place> findOneByName(String name); //retorna o primeiro que achar com esse nome ou null se nao achar nenhum

    //DOIS MÉTODOS EQUIVALENTES

    List<Place> findListByName(String name);
    // @Query("SELECT * FROM `tb places` WHERE name = '...'") pedrin

    @Query("FROM Place p WHERE p.name = :name") //JPQL - sql orientado a objs
    List<Place> findPlacesFilteredByName(@Param("name") String name);




}




