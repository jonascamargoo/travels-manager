package br.com.jonascamargo.travelsmanager.repositories;

import java.util.List;
//import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jonascamargo.travelsmanager.domain.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    //Optional<Ticket> findByPassengerName(String passengerName);
    @Query("SELECT t FROM Ticket t WHERE t.source.name = :placeName")
    List<Ticket> findTicketsBySourceName(@Param("placeName") String placeName);
    @Query("SELECT t FROM Ticket t WHERE t.destination.name =:placeName")
    List<Ticket> findTicketsByDestinationName(@Param("placeName") String placeName);

    
}
