package br.com.jonascamargo.travelsmanager.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonascamargo.travelsmanager.domain.dtos.TicketRecordDTO;
import br.com.jonascamargo.travelsmanager.domain.models.Ticket;
import br.com.jonascamargo.travelsmanager.services.logic.TicketService;
import jakarta.validation.Valid;

@RestController
// @CrossOrigin(origins = )
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/")
    public ResponseEntity<Ticket> createTicket(@RequestBody @Valid TicketRecordDTO ticketRecordDTO) {
        Ticket createdTicket = ticketService.createTicket(ticketRecordDTO);
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

    @DeleteMapping("/")
    public ResponseEntity<Object> deleteAllTickets() {
        this.ticketService.deleteTickets();
        return ResponseEntity.status(HttpStatus.OK).body("All tickets deleted successfully.");
    }

}