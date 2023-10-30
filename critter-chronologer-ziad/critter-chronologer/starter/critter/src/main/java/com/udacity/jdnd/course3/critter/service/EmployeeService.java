package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepo;

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
    public List<Employee> getEmployeesByService(LocalDate date, Set<EmployeeSkill> skills){
        return employeeRepo
                .findByDaysAvailable(date.getDayOfWeek()).stream()
                .filter(employee -> employee.getEmployeeSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepo.getOne(employeeId);
    }
    public void setEmployeeAvailability(Set<DayOfWeek> days, Long employeeId) {
        Employee employee = employeeRepo.getOne(employeeId);
        employee.setDaysAvailable(days);
        employeeRepo.save(employee);
    }

}
