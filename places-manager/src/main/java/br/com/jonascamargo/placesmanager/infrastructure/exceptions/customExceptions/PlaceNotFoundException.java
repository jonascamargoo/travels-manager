package br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException() {
        super("Lugar não encontrado!");
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }

}
