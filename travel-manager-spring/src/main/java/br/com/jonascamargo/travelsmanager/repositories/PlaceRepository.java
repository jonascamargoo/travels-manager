package br.com.jonascamargo.travelsmanager.repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jonascamargo.travelsmanager.domain.models.Place;

public interface PlaceRepository extends JpaRepository<Place, UUID>{
    Optional<Place> findByName(String name);
    List<Place> findListByName(String name);
    @Query("FROM Place p WHERE p.name = :name")
    List<Place> findPlacesFilteredByName(@Param("name") String name);
    @Modifying
    @Query("DELETE FROM Place p WHERE p.id = :id AND NOT EXISTS (SELECT t.id FROM Ticket t WHERE t.source.id = :id OR t.destination.id = :id)")
    void deleteByIdWithoutAssociatedTickets(@Param("id") UUID id);

}




