package br.com.jonascamargo.travelsmanager.logic.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.travelsmanager.enums.PaymentMethod;
import br.com.jonascamargo.travelsmanager.infrastructure.dtos.PaymentRecordDto;
import br.com.jonascamargo.travelsmanager.infrastructure.models.Passenger;
import br.com.jonascamargo.travelsmanager.infrastructure.models.Payment;
import br.com.jonascamargo.travelsmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.PassengerRepository;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.PaymentRepository;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.TicketRepository;
import br.com.jonascamargo.travelsmanager.logic.CreditCardValidation;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private TicketRepository ticketRepository;
    private PassengerRepository passengerRepository;
    private Slugify slug;
    private CreditCardValidation creditCardValidation;

    public PaymentService(PaymentRepository paymentRepository, TicketRepository ticketRepository,
            PassengerRepository passengerRepository) {
        this.paymentRepository = paymentRepository;
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.slug = Slugify.builder().build();
    }

    // Utilizar o RabbitMQ para enviar a confirmacao de pagamento
    public Payment createPayment(PaymentRecordDto paymentRecordDto) {
        // Retrieve the Ticket object based on the provided ticketId
        Ticket ticket = ticketRepository.findById(paymentRecordDto.ticketId())
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        // Retrieve the Passenger object based on the provided passengerId
        Passenger passenger = passengerRepository.findById(paymentRecordDto.passengerId())
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found"));
        // Validate payment at the beginning
        if (!isValidPayment(paymentRecordDto, ticket, passenger)) {
            throw new IllegalArgumentException("Payment invalid.");
        }
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentRecordDto, payment);
        payment.setSlug(slug.slugify(paymentRecordDto.description()));
        // Save Payment
        return paymentRepository.save(payment);
    }

    public boolean isValidPayment(PaymentRecordDto paymentRecordDto, Ticket ticket, Passenger passenger) {
        return isAmountEnough(paymentRecordDto, ticket) &&
                isPassengerLegalAge(passenger) &&
                isPaymentMethodValid(paymentRecordDto);

    }

    public boolean isAmountEnough(PaymentRecordDto paymentRecordDto, Ticket ticket) {
        return paymentRecordDto.amount().compareTo(ticket.getPrice()) > 0;
    }

    public boolean isPassengerLegalAge(Passenger passenger) {
        return passenger.getAge() >= 18;
    }

    public boolean isPaymentMethodValid(PaymentRecordDto paymentRecordDto) {

        PaymentMethod paymentMethod = paymentRecordDto.paymentMethod();
        if (paymentMethod == PaymentMethod.CREDIT_CARD) {
            creditCardValidation = new CreditCardValidation();
            return creditCardValidation.isCreditCardValid(paymentRecordDto.cardDigits());
        }
        return true; // boleto aways valid
    }

    public void deletePayments() {
        paymentRepository.deleteAll();
    }

}
