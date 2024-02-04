package br.com.jonascamargo.travelsmanager.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.jonascamargo.travelsmanager.enums.PaymentMethod;
import br.com.jonascamargo.travelsmanager.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_Payments")
@Getter
@Setter
public class Payment extends RepresentationModel<Payment> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private UUID idPayment;
    private String description;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private String slug;
    private LocalDateTime paymentTime;
    private PaymentMethod paymentMethod;
    private Passenger buyer;

}
