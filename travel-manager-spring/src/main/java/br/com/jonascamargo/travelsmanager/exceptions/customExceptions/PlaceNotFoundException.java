package br.com.jonascamargo.travelsmanager.exceptions.customExceptions;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException() {
        super("Lugar não encontrado!");
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }

}

