package br.com.jonascamargo.travelsmanager.config;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.jonascamargo.travelsmanager.infrastructure.models.Place;
import br.com.jonascamargo.travelsmanager.infrastructure.models.Ticket;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.PassengerRepository;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.PaymentRepository;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.PlaceRepository;
import br.com.jonascamargo.travelsmanager.infrastructure.repositories.TicketRepository;

@Configuration
public class PopulateDatabase {
    @Bean
    CommandLineRunner initDatabase(
            PlaceRepository placeRepository,
            TicketRepository ticketRepository,
            PassengerRepository passengerRepository,
            PaymentRepository paymentRepository) {
        return args -> {
            // Cleaning existing data
            paymentRepository.deleteAll();
            ticketRepository.deleteAll();
            placeRepository.deleteAll();
            passengerRepository.deleteAll();

            // Populating the database with some example data
            // Creating after places to avoid PlaceNotFoundException
            Random rd = new Random();
            for (int i = 0; i < 5; i += 2) {

                // Create places
                Place place0 = new Place();
                place0.setName("place" + i);
                place0.setSlug("place" + i);
                placeRepository.save(place0);
                Place place1 = new Place();
                int srcIndex = i + 1;
                int destIndex = srcIndex;
                place1.setName("place" + srcIndex);
                place1.setSlug("place" + destIndex);
                placeRepository.save(place1);

                // Create ticket
                Ticket ticket = new Ticket();
                ticket.setSource(place0);
                ticket.setDestination(place1);
                ticket.setPrice(new BigDecimal(rd.nextDouble() * 10));
                ticket.setDepartureTime(LocalDateTime.now().minus(30 + i, ChronoUnit.MINUTES));
                ticket.setPurchaseTime(LocalDateTime.now().plus(1, ChronoUnit.HOURS));
                ticketRepository.save(ticket);
            }
        };
    }
}