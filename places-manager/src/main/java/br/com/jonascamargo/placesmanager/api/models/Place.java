package br.com.jonascamargo.placesmanager.api.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//ELIMINAR OS GETTERS E SETTER INUTEIS

@Entity
@Table(name = "TB PLACES")
public class Place extends RepresentationModel<Place> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private String city;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    

}
