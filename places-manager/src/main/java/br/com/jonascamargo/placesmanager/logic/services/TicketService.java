package br.com.jonascamargo.placesmanager.logic.services;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.TicketRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private Slugify slug;
    private boolean available;
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.slug = Slugify.builder().build();
        this.available = true;
    }

    public Ticket createTicket(TicketRecordDto ticketRecordDto) { 
        Ticket ticketO = new Ticket();
        BeanUtils.copyProperties(ticketRecordDto, ticketO);
        ticketO.setSlug(slug.slugify(ticketRecordDto.destination()));
        return ticketRepository.save(ticketO);
        
    }

    

    
    public boolean isTicketTimeStillValid(Ticket ticket) {
        Duration duration = Duration.between(ticket.getPurchaseTime(), ticket.getDepartureTime());
        return duration.toMinutes() >= 30;
        // nao comprar em cima da hora (30 min antes)
        
    }
    
}



