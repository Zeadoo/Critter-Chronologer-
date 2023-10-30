package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long scheduleId;
    private LocalDate scheduleDate;

    @ElementCollection
    private Set<EmployeeSkill> scheduleActivities;

    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employee;

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;


    public Schedule(LocalDate date, Set<EmployeeSkill> activities) {
        this.scheduleDate = date;
        this.scheduleActivities = activities;
    }

    public Schedule() {
    }

    public Long getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }
    public List<Employee> getEmployee() {
        return employee;
    }
    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
    public LocalDate getScheduleDate() {
        return scheduleDate;
    }
    public Set<EmployeeSkill> getScheduleActivities() {return scheduleActivities;}
}
