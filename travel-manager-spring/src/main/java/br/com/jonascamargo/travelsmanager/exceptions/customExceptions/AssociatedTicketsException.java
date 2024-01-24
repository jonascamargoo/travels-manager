package br.com.jonascamargo.travelsmanager.exceptions.customExceptions;

public class AssociatedTicketsException extends RuntimeException {
    public AssociatedTicketsException() {
        super("Remoção falhou. Existem um ou mais tickets associados a este local!");
    }
    
    public AssociatedTicketsException(String message) {
        super(message);
    }

}
