// package br.com.jonascamargo.placesmanager.logic.services;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

// import org.junit.Test;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.mockito.ArgumentMatchers;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.TestConfiguration;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Bean;

// import br.com.jonascamargo.placesmanager.ApplicationConfigTest;
// import br.com.jonascamargo.placesmanager.infrastructure.models.Ticket;
// import br.com.jonascamargo.placesmanager.infrastructure.repositories.TicketRepository;

// @DisplayName("TicketServiceTest")
// public class TicketServiceTest extends ApplicationConfigTest {

//     @TestConfiguration
//     static class TicketServiceTestConfig {
//         @Bean
//         public TicketService ticketService(TicketRepository ticketRepository) {
//             return new TicketService(ticketRepository);
//         }
//     }

//     @Autowired
//     TicketService ticketService;

//     @MockBean // pegamos os atributos do service e colocamos aqui como MockBean
//     TicketRepository ticketRepository;

//     @BeforeEach
//     public void setup() {
//         LocalDateTime purchaseTime = LocalDateTime.of(2023, 7, 17, 10, 0, 0);
//         LocalDateTime departureTime = LocalDateTime.of(2023, 7, 17, 10, 15, 0);
//         ticketService = new TicketService(ticketRepository);

//         Ticket ticket = new Ticket();
//         ticket.setDepartureTime(departureTime);
//         ticket.setPurchaseTime(purchaseTime);

//         Mockito.when(ticketRepository.findByPassengerName(Mockito.anyString()))
//                 .thenReturn(java.util.Optional.of(ticket));

//     }

//     @Test
//     public void testIsTicketTimeStillValid() {
//         // Crie um objeto Ticket que representa um ticket válido
//         Ticket validTicket = new Ticket();
//         TicketService ticketService = new TicketService(ticketRepository);
//         validTicket.setDepartureTime(LocalDateTime.now().plusMinutes(30));
//         validTicket.setPurchaseTime(LocalDateTime.now().minusMinutes(30));

//         // // Verifique se o ticket válido retorna true para isTicketTimeStillValid()
//         boolean validity = ticketService.isTicketTimeStillValid(validTicket);
//         Assertions.assertTrue(validity);

//         // Crie um objeto Ticket que representa um ticket inválido
//         Ticket invalidTicket = new Ticket();
//         invalidTicket.setDepartureTime(LocalDateTime.now().plusMinutes(10));
//         invalidTicket.setPurchaseTime(LocalDateTime.now().minusMinutes(30));

//         // Verifique se o ticket inválido retorna false para isTicketTimeStillValid()
//         validity = ticketService.isTicketTimeStillValid(invalidTicket);
//         Assertions.assertFalse(validity);
//     }


//     // @Test
//     // @DisplayName("deve remover ticket")
//     // public void deveRemoverVenda() {
//     //     String ticketId = "id-mock";
//     //     Ticket ticket = Mockito.mock(Ticket.class);
//     //     Mockito.when(ticket.getValorRecebido()).thenReturn(BigDecimal.TEN);
//     //     Mockito.when(ticket.getItens()).thenReturn(Collections.emptyList());
//     //     Mockito.when(ticket.getValorTotal()).thenReturn(BigDecimal.TEN);
//     //     Optional<Venda> vendaOptoOptional = Optional.of(venda);
//     //     Mockito.when(repository.findById(ArgumentMatchers.eq(vendaId))).thenReturn(vendaOptoOptional);

//     //     vendaService.removerVenda(vendaId);
//     //     Mockito.verify(repository, Mockito.times(1)).delete(ArgumentMatchers.any(Venda.class));
//     //     Mockito.verify(estoqueService, Mockito.never()).adicionarNoEstoque(ArgumentMatchers.any(), ArgumentMatchers.any());
//     //     Mockito.verify(titulosReceberService, Mockito.never()).removerTitulo(ArgumentMatchers.any());
//     // }

// }
