package br.com.jonascamargo.placesmanager.infrastructure.exception.customExceptions;

public class PassengerNotFoundException extends RuntimeException{
    public PassengerNotFoundException() {
        super("Passageiro não encontrado!");
    }

    public PassengerNotFoundException(String message) {
        super(message);
    }
}