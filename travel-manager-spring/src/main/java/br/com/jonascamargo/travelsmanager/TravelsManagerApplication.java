package br.com.jonascamargo.travelsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class TravelsManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelsManagerApplication.class, args);
	}

    
}