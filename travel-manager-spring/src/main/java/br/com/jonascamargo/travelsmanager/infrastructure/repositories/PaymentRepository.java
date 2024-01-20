package br.com.jonascamargo.travelsmanager.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonascamargo.travelsmanager.infrastructure.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{
    
}
