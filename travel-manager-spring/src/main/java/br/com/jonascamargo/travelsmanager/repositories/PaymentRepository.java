package br.com.jonascamargo.travelsmanager.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonascamargo.travelsmanager.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{
    
}
