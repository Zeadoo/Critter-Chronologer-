package com.udacity.jdnd.course3.critter.data;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String customerNotes;
    private String customerPhoneNumber;
    @Nationalized                   //support international language characters
    private String customerName;
    @OneToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    public Customer(Long id, String name, String phoneNumber, String notes) {
        this.id = id;
        this.customerNotes = notes;
        this.customerPhoneNumber = phoneNumber;
        this.customerName = name;
    }

    public Customer() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    public String getCustomerNotes() {
        return customerNotes;
    }
    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
