package br.com.jonascamargo.placesmanager.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.InvalidTicketTimeException;
import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.PassengerNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.PlaceNotFoundException;
import br.com.jonascamargo.placesmanager.infrastructure.exceptions.customExceptions.TicketNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PlaceNotFoundException.class)
    private ResponseEntity<RestErrorMessage> placeNotFoundHandler(PlaceNotFoundException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(PassengerNotFoundException.class)
    private ResponseEntity<RestErrorMessage> passengerNotFoundHandler(PassengerNotFoundException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    private ResponseEntity<RestErrorMessage> ticketNotFoundHandler(TicketNotFoundException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(InvalidTicketTimeException.class)
    private ResponseEntity<RestErrorMessage> invalidTicketTimeHandler(InvalidTicketTimeException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResponse);
    }

}