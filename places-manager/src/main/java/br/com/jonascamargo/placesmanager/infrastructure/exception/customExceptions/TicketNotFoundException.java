package br.com.jonascamargo.placesmanager.infrastructure.exception.customExceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException() {
        super("Ticket não encontrado!");
    }

    public TicketNotFoundException(String message) {
        super(message);
    }
}
