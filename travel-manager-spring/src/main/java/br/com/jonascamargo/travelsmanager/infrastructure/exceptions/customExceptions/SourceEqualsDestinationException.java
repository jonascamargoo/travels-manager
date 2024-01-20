package br.com.jonascamargo.travelsmanager.infrastructure.exceptions.customExceptions;

public class SourceEqualsDestinationException extends RuntimeException {
    public SourceEqualsDestinationException() {
        super("O local de partida e destino não podem ser iguais!");
    }

    public SourceEqualsDestinationException(String message) {
        super(message);
    }
}
