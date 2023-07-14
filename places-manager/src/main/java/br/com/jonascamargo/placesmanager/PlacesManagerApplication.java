package br.com.jonascamargo.placesmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //esta aqui para habilitar o Listener pra auditoria. Tirar quando criar o config
public class PlacesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlacesManagerApplication.class, args);
	}





}


