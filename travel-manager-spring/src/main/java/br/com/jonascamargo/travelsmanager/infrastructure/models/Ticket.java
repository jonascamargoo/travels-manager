package br.com.jonascamargo.travelsmanager.infrastructure.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_TICKETS")
@EntityListeners(AuditingEntityListener.class)
public class Ticket extends RepresentationModel<Ticket> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getIdTicket() {
        return idTicket;
    }
    
    public Place getSource() {
        return source;
    }
    public void setSource(Place source) {
        this.source = source;
    }
    public Place getDestination() {
        return destination;
    }
    public void setDestination(Place destination) {
        this.destination = destination;
    }
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }
    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
   
}