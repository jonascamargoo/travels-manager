package br.com.jonascamargo.placesmanager.logic.services;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.TicketRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.InvalidTicketTimeException;
import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.PlaceNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.TicketNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final PlaceService placeService;
    private Slugify slug;

    public TicketService(TicketRepository ticketRepository, PlaceService placeService) {
        this.ticketRepository = ticketRepository;
        this.placeService = placeService;
        this.slug = Slugify.builder().build();
    }

    public Ticket createTicket(TicketRecordDto ticketRecordDto) {
        if (!isTicketTimeStillValid(ticketRecordDto)) {
            throw new InvalidTicketTimeException();
        }
        if(
            // validando se existe o lugar para emitir o ticket
            !placeService.existsByName(ticketRecordDto.source()) &&
            !placeService.existsByName(ticketRecordDto.destination())
        ) {
            throw new PlaceNotFoundException();
        }

        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketRecordDto, ticket);
        ticket.setSlug(slug.slugify(ticketRecordDto.destination()));
        return ticketRepository.save(ticket);

    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(UUID id) {
        return ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
    }

    // the purchase is only allowed 30 minutes prior to the departure time
    public boolean isTicketTimeStillValid(TicketRecordDto ticketRecordDto) {
        Duration duration = Duration.between(ticketRecordDto.purchaseTime(), ticketRecordDto.departureTime());
        return duration.toMinutes() >= 30;
    }

}
