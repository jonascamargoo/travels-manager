package br.com.jonascamargo.placesmanager.infrastructure.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB TICKETS")
@EntityListeners(AuditingEntityListener.class)
public class Ticket extends RepresentationModel<Ticket> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String passengerName;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private BigDecimal price;
    private LocalDateTime purchaseTime;

}
