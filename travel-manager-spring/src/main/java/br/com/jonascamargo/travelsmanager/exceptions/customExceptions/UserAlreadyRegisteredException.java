package br.com.jonascamargo.travelsmanager.exceptions.customExceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
        super("The user is already regitered");
    }

    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
