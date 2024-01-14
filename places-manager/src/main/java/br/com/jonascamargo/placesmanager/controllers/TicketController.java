package br.com.jonascamargo.placesmanager.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonascamargo.placesmanager.infrastructure.dtos.TicketRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.placesmanager.logic.services.TicketService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/passagens")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/")
    public ResponseEntity<Ticket> createTicket(@RequestBody @Valid TicketRecordDto ticketRecordDto) {
        Ticket createdTicket = ticketService.createTicket(ticketRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }

    @GetMapping("/")
    public ResponseEntity<List<Ticket>> getTickets() {
        List<Ticket> tickets = ticketService.getTickets();
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable(value = "id") UUID id) {
        Ticket ticket = ticketService.getTicketById(id);
        ticket.add(linkTo(methodOn(TicketController.class).getTickets()).withRel("Lista de passagens"));
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

}