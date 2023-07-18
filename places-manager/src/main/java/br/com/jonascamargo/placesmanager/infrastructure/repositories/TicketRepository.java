package br.com.jonascamargo.placesmanager.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonascamargo.placesmanager.infrastructure.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    Optional<Ticket> findByPassengerName(String passengerName);
    

    
}
