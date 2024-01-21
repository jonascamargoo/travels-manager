package br.com.jonascamargo.travelsmanager.logic.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jonascamargo.travelsmanager.infrastructure.dtos.TicketRecordDto;
import br.com.jonascamargo.travelsmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.TicketRepository;

@DisplayName("Ticket Service Tests")
public class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    TicketService ticketService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    @DisplayName("Check if Ticket Time is Valid")
    void isValidTicketTime() {
        // Create a TicketRecordDto with a purchase 25 minutes before departure
        // (invalid)
        TicketRecordDto invalidReq = new TicketRecordDto(
                "city0",
                "city1",
                new BigDecimal(100),
                LocalDateTime.now().minus(25, ChronoUnit.MINUTES),
                LocalDateTime.now().plus(0, ChronoUnit.HOURS));

        // Verify if the method returns false for an invalid time
        assertFalse(ticketService.isTicketTimeStillValid(invalidReq));

        // Create a TicketRecordDto with a purchase 91 minutes before departure (valid)
        TicketRecordDto validReq = new TicketRecordDto(
                "city0",
                "city1",
                new BigDecimal(100),
                LocalDateTime.now().minus(31, ChronoUnit.MINUTES),
                LocalDateTime.now().plus(1, ChronoUnit.HOURS));
        assertTrue(ticketService.isTicketTimeStillValid(validReq));
    }

    @Test
    @DisplayName("Should create a ticket successfully when everything is ok")
    void createTicket() {
        // valid
        TicketRecordDto validReq = new TicketRecordDto(
                "city0",
                "city1",
                new BigDecimal(100),
                LocalDateTime.now().minus(31, ChronoUnit.MINUTES),
                LocalDateTime.now().plus(1, ChronoUnit.HOURS));
        when(ticketRepository.save(any())).thenReturn(new Ticket());
        Ticket createdTicket = ticketService.createTicket(validReq);
        assertNotNull(createdTicket);
        verify(ticketRepository, times(1)).save(any());
    }

}