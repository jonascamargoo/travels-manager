// package br.com.jonascamargo.placesmanager.config;


// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.time.temporal.ChronoUnit;
// import java.util.Random;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Profile;

// import br.com.jonascamargo.placesmanager.infrastructure.models.Place;
// import br.com.jonascamargo.placesmanager.infrastructure.models.Ticket;
// import br.com.jonascamargo.placesmanager.infrastructure.repositories.PassengerRepository;
// import br.com.jonascamargo.placesmanager.infrastructure.repositories.PaymentRepository;
// import br.com.jonascamargo.placesmanager.infrastructure.repositories.PlaceRepository;
// import br.com.jonascamargo.placesmanager.infrastructure.repositories.TicketRepository;

// public class PopulateDataBase {
//     @Bean
//     CommandLineRunner populateDatabase(
//             PlaceRepository placeRepository,
//             TicketRepository ticketRepository,
//             PassengerRepository passengerRepository,
//             PaymentRepository paymentRepository) {
//         return args -> {
//             // Cleaning existing data
//             placeRepository.deleteAll();
//             ticketRepository.deleteAll();
//             passengerRepository.deleteAll();
//             paymentRepository.deleteAll();
            
//             // Populating the database with some example data
//             // Create places
//             for (int i = 0; i < 5; i++) {
//                 Place place = new Place();
//                 place.setName("place" + i);
//                 place.setSlug("place" + i);
//                 placeRepository.save(place);
//             }

//             // Creating after places to avoid PlaceNotFoundException
//             Random rd = new Random();
//             for (int i = 0; i < 5; i++) {
//                 Ticket ticket = new Ticket();
//                 ticket.setSource(placeRepository.findByName("place" + i).get());
//                 ticket.setDestination(placeRepository.findByName("place" + i + 1).get());
//                 ticket.setPrice(new BigDecimal(rd.nextDouble() * 10));
//                 ticket.setDepartureTime(LocalDateTime.now().minus(30 + i, ChronoUnit.MINUTES));
//                 ticket.setPurchaseTime(LocalDateTime.now().plus(1, ChronoUnit.HOURS));
//                 ticketRepository.save(ticket);
//             }
//         };
//     }
// }


