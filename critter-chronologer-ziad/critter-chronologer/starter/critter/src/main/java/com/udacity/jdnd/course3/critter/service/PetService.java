package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    PetRepository petRepo;
    public Pet savePet(Pet pet, Long customerId) {
        List<Pet> pets = new ArrayList<>();
        Customer cust = customerRepo.getOne(customerId);
        pet.setCustomer(cust);
        pet = petRepo.save(pet);
        pets.add(pet);
        cust.setPets(pets);
        customerRepo.save(cust);

        return pet;
    }
    public List<Pet> getPetsByCustomerId(long customerId) {
        return petRepo.findPetByCustomerId(customerId);
    }
    public List<Pet> getAllPets() {
        return petRepo.findAll();
    }
    public Pet getPetById(Long petId) {
        return petRepo.getOne(petId);
    }
}
