package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class Pet {
    @Nationalized
    private String petName;
    @Id
    @GeneratedValue
    private Long petId;
    private PetType petType;
    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;
    private LocalDate birthDate;
    private String notes;

    public Pet(PetType type, String name, LocalDate birthDate, String notes) {
        this.petName = name;
        this.petType = type;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Pet() {
    }
    public Long getPetId() {
        return petId;
    }
    public PetType getPetType() {
        return petType;
    }
    public String getPetName() {
        return petName;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getNotes() {
        return notes;
    }
}
