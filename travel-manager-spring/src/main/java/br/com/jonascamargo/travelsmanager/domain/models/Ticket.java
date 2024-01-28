package br.com.jonascamargo.travelsmanager.domain.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_TICKETS")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Ticket extends RepresentationModel<Ticket> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private UUID idTicket;

    @ManyToOne
    @JoinColumn(name = "source_id", nullable = false)
    private Place source;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Place destination;

    private LocalDateTime departureTime;
    private BigDecimal price;
    private LocalDateTime purchaseTime;
    private String slug;

}
