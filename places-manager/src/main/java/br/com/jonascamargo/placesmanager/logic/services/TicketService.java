package br.com.jonascamargo.placesmanager.logic.services;

import java.time.Duration;

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
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.slug = Slugify.builder().build();
    }

    public Ticket createTicket(TicketRecordDto ticketRecordDto) {
        if(!isTicketTimeStillValid(ticketRecordDto)) {
            throw new IllegalArgumentException("Ticket time is no longer valid.");
        }

        Ticket ticketO = new Ticket();
        BeanUtils.copyProperties(ticketRecordDto, ticketO);
        ticketO.setSlug(slug.slugify(ticketRecordDto.destination()));
        return ticketRepository.save(ticketO);
        
    }

    // the purchase is only allowed 30 minutes prior to the departure time
    public boolean isTicketTimeStillValid(TicketRecordDto ticketRecordDto) {
        Duration duration = Duration.between(ticketRecordDto.departureTime(), ticketRecordDto.purchaseTime());
        return duration.toMinutes() >= 30;
    }
    
    
}



