package br.com.jonascamargo.travelsmanager.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;

import br.com.jonascamargo.travelsmanager.domain.dtos.PaymentRecordDTO;
import br.com.jonascamargo.travelsmanager.domain.models.Passenger;
import br.com.jonascamargo.travelsmanager.domain.models.Payment;
import br.com.jonascamargo.travelsmanager.domain.models.Ticket;
import br.com.jonascamargo.travelsmanager.enums.PaymentMethod;
import br.com.jonascamargo.travelsmanager.repositories.PassengerRepository;
import br.com.jonascamargo.travelsmanager.repositories.PaymentRepository;
import br.com.jonascamargo.travelsmanager.repositories.TicketRepository;


@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private TicketRepository ticketRepository;
    private PassengerRepository passengerRepository;
    private Slugify slug;
    private CreditCardValidationService creditCardValidation;

    public PaymentService(PaymentRepository paymentRepository, TicketRepository ticketRepository,
            PassengerRepository passengerRepository) {
        this.paymentRepository = paymentRepository;
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.slug = Slugify.builder().build();
    }

    // Utilizar o RabbitMQ para enviar a confirmacao de pagamento
    public Payment createPayment(PaymentRecordDTO paymentRecordDTO) {
        // Retrieve the Ticket object based on the provided ticketId
        Ticket ticket = ticketRepository.findById(paymentRecordDTO.ticketId())
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        // Retrieve the Passenger object based on the provided passengerId
        Passenger passenger = passengerRepository.findById(paymentRecordDTO.passengerId())
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found"));
        // Validate payment at the beginning
        if (!isValidPayment(paymentRecordDTO, ticket, passenger)) {
            throw new IllegalArgumentException("Payment invalid.");
        }
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentRecordDTO, payment);
        payment.setSlug(slug.slugify(paymentRecordDTO.description()));
        // Save Payment
        return paymentRepository.save(payment);
    }

    public boolean isValidPayment(PaymentRecordDTO paymentRecordDTO, Ticket ticket, Passenger passenger) {
        return isAmountEnough(paymentRecordDTO, ticket) &&
                isPassengerLegalAge(passenger) &&
                isPaymentMethodValid(paymentRecordDTO);

    }

    public boolean isAmountEnough(PaymentRecordDTO paymentRecordDTO, Ticket ticket) {
        return paymentRecordDTO.amount().compareTo(ticket.getPrice()) > 0;
    }

    public boolean isPassengerLegalAge(Passenger passenger) {
        return passenger.getAge() >= 18;
    }

    public boolean isPaymentMethodValid(PaymentRecordDTO paymentRecordDTO) {

        PaymentMethod paymentMethod = paymentRecordDTO.paymentMethod();
        if (paymentMethod == PaymentMethod.CREDIT_CARD) {
            creditCardValidation = new CreditCardValidationService();
            return creditCardValidation.isCreditCardValid(paymentRecordDTO.cardDigits());
        }
        return true; // boleto aways valid
    }

    public void deletePayments() {
        paymentRepository.deleteAll();
    }

}
