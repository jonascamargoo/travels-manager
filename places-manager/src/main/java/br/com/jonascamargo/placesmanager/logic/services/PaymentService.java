package br.com.jonascamargo.placesmanager.logic.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.placesmanager.enums.PaymentMethod;
import br.com.jonascamargo.placesmanager.infrastructure.dtos.PaymentRecordDto;
import br.com.jonascamargo.placesmanager.infrastructure.models.Passenger;
import br.com.jonascamargo.placesmanager.infrastructure.models.Payment;
import br.com.jonascamargo.placesmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PassengerRepository;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PaymentRepository;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.TicketRepository;
import br.com.jonascamargo.placesmanager.logic.CreditCardValidation;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private TicketRepository ticketRepository;
    private PassengerRepository passengerRepository;
    private Slugify slug;

    public PaymentService(PaymentRepository paymentRepository, TicketRepository ticketRepository, PassengerRepository passengerRepository) {
        this.paymentRepository = paymentRepository; //DEVO REALMENTE TRAZER MAIS ESSA DEPENDENCIA (TICKETREPOSITORY PRA CA???)
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.slug = Slugify.builder().build();
    }

    // Utilizar o RabbitMQ para enviar a confirmacao de pagamento
    public Payment createPayment(PaymentRecordDto paymentRecordDto) {
        // Retrieve the Ticket object based on the provided ticketId
        Optional<Ticket> ticket = ticketRepository.findById(paymentRecordDto.ticketId());
        Optional<Passenger> passenger = passengerRepository.findById(paymentRecordDto.passengerId());

        if(!isValidPayment(paymentRecordDto, ticket.get(), passenger.get()))
            throw new IllegalArgumentException("Payment invalid.");
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentRecordDto, payment);
        payment.setSlug(slug.slugify(paymentRecordDto.description()));
        return paymentRepository.save(payment);
    }

    public boolean isValidPayment(PaymentRecordDto paymentRecordDto, Ticket ticket, Passenger passenger) {
        return  isAmountEnough(paymentRecordDto, ticket) &&
                isPassengerLegalAge(passenger) &&
                isPaymentMethodValid(paymentRecordDto);

    }


    public boolean isAmountEnough(PaymentRecordDto paymentRecordDto,Ticket ticket) {
        return paymentRecordDto.amount().compareTo(ticket.getPrice()) > 0;
    }

    public boolean isPassengerLegalAge(Passenger passenger) {
        return passenger.getAge() >= 18;
    }

    public boolean isPaymentMethodValid(PaymentRecordDto paymentRecordDto) {
        PaymentMethod paymentMethod = paymentRecordDto.paymentMethod();
        if(paymentMethod == PaymentMethod.CREDIT_CARD) {
            return CreditCardValidation.isCreditCardValid(paymentRecordDto.cardDigits());
        }
        return true; //validacao por boleto sempre verdadeira
    }

    


 
    
}
