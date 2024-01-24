package br.com.jonascamargo.travelsmanager.domain.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PASSENGERS")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Passenger extends RepresentationModel<Passenger> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private UUID idPassenger;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private String slug;

}
