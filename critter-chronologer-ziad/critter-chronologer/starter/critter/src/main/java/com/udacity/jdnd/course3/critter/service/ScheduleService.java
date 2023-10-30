package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepo;
    @Autowired
    PetRepository petRepo;
    @Autowired
    EmployeeRepository employeeRepo;
    @Autowired
    CustomerRepository customerRepo;

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        schedule.setPets(petRepo.findAllById(petIds));
        schedule.setEmployee(employeeRepo.findAllById(employeeIds));
        return scheduleRepo.save(schedule);
    }
    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }
    public List<Schedule> getEmployeeSchedule(Long employeeId) {
        return scheduleRepo.findByEmployee(employeeRepo.getOne(employeeId));
    }

    public List<Schedule> getPetSchedule(Long petId) {
        return scheduleRepo.findByPets(petRepo.getOne(petId));
    }

    public List<Schedule> getCustomerSchedule(Long customerId) {
        return scheduleRepo.findByPetsIn(customerRepo.getOne(customerId).getPets());
    }

}
