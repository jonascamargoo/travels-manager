package br.com.jonascamargo.placesmanager.infrastructure.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import br.com.jonascamargo.placesmanager.enums.TicketStatus;
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
    private Place source;
    private Place destination;
    private LocalDateTime departureTime;
    private BigDecimal price;
    private LocalDateTime purchaseTime;
    private String slug;
    private TicketStatus ticketStatus;

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }
    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
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
