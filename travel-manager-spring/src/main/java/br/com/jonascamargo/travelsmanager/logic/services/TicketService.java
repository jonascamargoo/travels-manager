package br.com.jonascamargo.travelsmanager.logic.services;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.travelsmanager.infrastructure.dtos.TicketRecordDto;
import br.com.jonascamargo.travelsmanager.infrastructure.exceptions.customExceptions.InvalidTicketTimeException;
import br.com.jonascamargo.travelsmanager.infrastructure.exceptions.customExceptions.PlaceNotFoundException;
import br.com.jonascamargo.travelsmanager.infrastructure.exceptions.customExceptions.SourceEqualsDestinationException;
import br.com.jonascamargo.travelsmanager.infrastructure.exceptions.customExceptions.TicketNotFoundException;
import br.com.jonascamargo.travelsmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.TicketRepository;

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
        if(ticketRecordDto.source().equals(ticketRecordDto.destination())) {
            throw new SourceEqualsDestinationException();
        }
        if(
            !placeService.existsByName(ticketRecordDto.source()) ||
            !placeService.existsByName(ticketRecordDto.destination())
        ) {
            throw new PlaceNotFoundException();
        }
        
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketRecordDto, ticket);
        ticket.setSource(placeService.getPlaceByName(ticketRecordDto.source()));
        ticket.setDestination(placeService.getPlaceByName(ticketRecordDto.destination()));
        ticket.setSlug(slug.slugify(ticketRecordDto.source() + "-" + ticketRecordDto.destination()));
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

    public List<Ticket> getTicketsSourceByName(String placeName) {
        return ticketRepository.findTicketsBySourceName(placeName);
    }

    public List<Ticket> getTicketsDestinationByName(String placeName) {
        return ticketRepository.findTicketsByDestinationName(placeName);
    }

    public boolean hasAssociatedTicket(String placeName) {
        return  getTicketsSourceByName(placeName).isEmpty() &&
                getTicketsDestinationByName(placeName).isEmpty();
    }

    public void deleteTickets() {
        ticketRepository.deleteAll();
    }

}
