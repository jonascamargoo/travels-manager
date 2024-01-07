package br.com.jonascamargo.placesmanager.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.jonascamargo.placesmanager.infrastructure.exception.customExceptions.PassengerNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.exception.customExceptions.PlaceNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.exception.customExceptions.TicketNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlaceNotFoundException.class)
    private ResponseEntity<String> placeNotFoundHandler(PlaceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Place not found.");
    }

    @ExceptionHandler(PassengerNotFoundException.class)
    private ResponseEntity<String> passengerNotFoundHandler(PassengerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger not found");
    }

    @ExceptionHandler(TicketNotFoundException.class)
    private ResponseEntity<String> ticketNotFoundHandler(TicketNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
    }

}
