package br.com.jonascamargo.placesmanager.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonascamargo.placesmanager.infrastructure.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{
    
}
