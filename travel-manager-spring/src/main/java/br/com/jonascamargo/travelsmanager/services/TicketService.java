package br.com.jonascamargo.travelsmanager.services;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.travelsmanager.domain.dtos.TicketRecordDTO;
import br.com.jonascamargo.travelsmanager.domain.models.Ticket;
import br.com.jonascamargo.travelsmanager.exceptions.customExceptions.InvalidTicketTimeException;
import br.com.jonascamargo.travelsmanager.exceptions.customExceptions.PlaceNotFoundException;
import br.com.jonascamargo.travelsmanager.exceptions.customExceptions.SourceEqualsDestinationException;
import br.com.jonascamargo.travelsmanager.exceptions.customExceptions.TicketNotFoundException;
import br.com.jonascamargo.travelsmanager.repositories.TicketRepository;

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

  
    public Ticket createTicket(TicketRecordDTO ticketRecordDTO) {
        if (!isTicketTimeStillValid(ticketRecordDTO)) {
            throw new InvalidTicketTimeException();
        }
        if(ticketRecordDTO.source().equals(ticketRecordDTO.destination())) {
            throw new SourceEqualsDestinationException();
        }
        if(
            !placeService.existsByName(ticketRecordDTO.source()) ||
            !placeService.existsByName(ticketRecordDTO.destination())
        ) {
            throw new PlaceNotFoundException();
        }
        
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketRecordDTO, ticket);
        ticket.setSource(placeService.getPlaceByName(ticketRecordDTO.source()));
        ticket.setDestination(placeService.getPlaceByName(ticketRecordDTO.destination()));
        ticket.setSlug(slug.slugify(ticketRecordDTO.source() + "-" + ticketRecordDTO.destination()));
        return ticketRepository.save(ticket);

    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(UUID id) {
        return ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
    }

    // the purchase is only allowed 30 minutes prior to the departure time
    public boolean isTicketTimeStillValid(TicketRecordDTO ticketRecordDTO) {
        Duration duration = Duration.between(ticketRecordDTO.purchaseTime(), ticketRecordDTO.departureTime());
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
