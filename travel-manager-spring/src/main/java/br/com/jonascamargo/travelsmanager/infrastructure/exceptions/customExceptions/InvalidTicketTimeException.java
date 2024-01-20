package br.com.jonascamargo.travelsmanager.infrastructure.exceptions.customExceptions;

public class InvalidTicketTimeException extends RuntimeException {
    public InvalidTicketTimeException() {
        super("Emissão fora do prazo. Ticket Invalido!");
    }

    public InvalidTicketTimeException(String message) {
        super(message);
    }
}
