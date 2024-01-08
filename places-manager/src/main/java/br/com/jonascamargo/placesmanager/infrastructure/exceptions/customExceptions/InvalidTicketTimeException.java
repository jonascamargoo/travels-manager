package br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions;

public class InvalidTicketTimeException extends RuntimeException {
    public InvalidTicketTimeException() {
        super("Compra fora do prazo. Ticket Invalido");
    }

    public InvalidTicketTimeException(String message) {
        super(message);
    }
}
