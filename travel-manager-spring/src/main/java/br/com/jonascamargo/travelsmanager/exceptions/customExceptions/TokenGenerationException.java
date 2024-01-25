package br.com.jonascamargo.travelsmanager.exceptions.customExceptions;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException() {
        super("Error while generating token!");
    }

    public TokenGenerationException(String message) {
        super(message);
    }
}
