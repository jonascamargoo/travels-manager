package br.com.jonascamargo.placesmanager.infrastructure.models;

import java.io.Serializable;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB PASSENGERS")
@EntityListeners(AuditingEntityListener.class)
public class Passenger extends RepresentationModel<Passenger> implements Serializable {
    private String name;
    private String email;
    private String phoneNumber; //create a validator
    private String slug;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }


    
}
