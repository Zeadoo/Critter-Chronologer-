package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    @Autowired
    PetRepository petRepo;
    @Autowired
    CustomerRepository customerRepo;

    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        if (petIds != null && !petIds.isEmpty()) {
            customer.setPets(petIds.stream().map((petId) -> petRepo.getOne(petId)).collect(Collectors.toList()));
        }
        else{
            customer.setPets(new ArrayList<>());
        }
        return customerRepo.save(customer);
    }

    public Customer getCustomerByPetId(Long petId) {
        return petRepo.getOne(petId).getCustomer();
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }
}
