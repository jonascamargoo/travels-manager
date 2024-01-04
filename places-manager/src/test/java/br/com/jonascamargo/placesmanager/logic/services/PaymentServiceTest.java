package br.com.jonascamargo.placesmanager.logic.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.jonascamargo.placesmanager.enums.PaymentMethod;
import br.com.jonascamargo.placesmanager.infrastructure.dtos.PaymentRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.models.Payment;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PassengerRepository;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PaymentRepository;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.TicketRepository;
import br.com.jonascamargo.placesmanager.logic.CreditCardValidation;

@DisplayName("Payment Service Tests")
public class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create payment successfully when everything is ok")
    void createPayment() {
        // Arrange
        CreditCardValidation ccvMock = mock(CreditCardValidation.class); // Criando um mock para CreditCardValidation
        PaymentRecordDto paymentRecordDto = new PaymentRecordDto(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "exemple description",
                BigDecimal.valueOf(100),
                LocalDateTime.now().minus(31, ChronoUnit.MINUTES), null,
                PaymentMethod.CREDIT_CARD,
                "123456789");
        
    
        when(paymentRepository.save(any())).thenReturn(new Payment());
        when(ccvMock.isCreditCardValid(anyString())).thenReturn(true);

        // Criando uma nova instância de PaymentService com o mock de CreditCardValidation
        PaymentService paymentService = new PaymentService(
            paymentRepository,
            ticketRepository,
            passengerRepository);
        // Act
        Payment createdPayment = paymentService.createPayment(paymentRecordDto);

        // Assert
        assertNotNull(createdPayment);
        verify(paymentRepository, times(1)).save(any());
        verify(ccvMock, times(1)).isCreditCardValid(any());
    }

}
