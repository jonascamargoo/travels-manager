package br.com.jonascamargo.travelsmanager.infrastructure.exceptions.customExceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException() {
        super("Ticket não encontrado!");
    }

    public TicketNotFoundException(String message) {
        super(message);
    }
}
