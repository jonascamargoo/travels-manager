package br.com.jonascamargo.placesmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import br.com.jonascamargo.placesmanager.infrastructure.repositories.PassengerRepository;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PaymentRepository;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.PlaceRepository;
import br.com.jonascamargo.placesmanager.infrastructure.repositories.TicketRepository;

@SpringBootApplication
@EnableJpaAuditing // esta aqui para habilitar o Listener pra auditoria. Tirar quando criar o
					// config
public class PlacesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlacesManagerApplication.class, args);
	}

	// // Inicializando alguns itens no banco de dados, apenas em tempo de
	// // desenvolvimento
	// @Bean
	// CommandLineRunner initDatabase(
	// 		PlaceRepository placeRepository,
	// 		PaymentRepository paymentRepository,
	// 		TicketRepository ticketRepository,
	// 		PassengerRepository passengerRepository) {
	// 	return args -> {
	// 		placeRepository.deleteAll();
	// 		.
	// 		.
	// 		.
	// 	};
	// }

}
