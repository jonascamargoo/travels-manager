package br.com.jonascamargo.placesmanager.logic.services;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import br.com.jonascamargo.placesmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.TicketRepository;

public class TicketServiceTest {

    @TestConfiguration
    static class TicketServiceTestConfig {
        @Bean
        public TicketService ticketService(TicketRepository ticketRepository) {
            return new TicketService(ticketRepository);
        }
    }

    @Autowired
    TicketService ticketService;

    @MockBean
    TicketRepository ticketRepository;

    @BeforeEach
    public void setup() {
        LocalDateTime purchaseTime = LocalDateTime.of(2023, 7, 17, 10, 0, 0);
        LocalDateTime departureTime = LocalDateTime.of(2023, 7, 17, 10, 15, 0);

        Ticket ticket = new Ticket();
        ticket.setDepartureTime(departureTime);
        ticket.setPurchaseTime(purchaseTime);

        Mockito.when(ticketRepository.findByPassengerName(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(ticket));

    }

    @Test
    public void testIsTicketTimeStillValid() {
        // Crie um objeto Ticket que representa um ticket válido
        Ticket validTicket = new Ticket();
        validTicket.setDepartureTime(LocalDateTime.now().plusMinutes(30));
        validTicket.setPurchaseTime(LocalDateTime.now().minusMinutes(30));

        // Verifique se o ticket válido retorna true para isTicketTimeStillValid()
        boolean validity = ticketService.isTicketTimeStillValid(validTicket);
        Assertions.assertTrue(validity);

        // Crie um objeto Ticket que representa um ticket inválido
        Ticket invalidTicket = new Ticket();
        invalidTicket.setDepartureTime(LocalDateTime.now().plusMinutes(10));
        invalidTicket.setPurchaseTime(LocalDateTime.now().minusMinutes(30));

        // Verifique se o ticket inválido retorna false para isTicketTimeStillValid()
        validity = ticketService.isTicketTimeStillValid(invalidTicket);
        Assertions.assertFalse(validity);
    }



}
