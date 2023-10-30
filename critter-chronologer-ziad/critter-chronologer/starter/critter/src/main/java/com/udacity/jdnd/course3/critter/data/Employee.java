package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Table
@Entity
public class Employee {
    private String employeeName;
    @Id
    @GeneratedValue
    private Long employeeId;

    @ElementCollection
    private Set<EmployeeSkill> employeeSkills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    public Employee(Long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.employeeName = name;
        this.employeeId = id;
        this.employeeSkills = skills;
        this.daysAvailable = daysAvailable;
    }
    public Employee() {
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public Set<EmployeeSkill> getEmployeeSkills() {
        return employeeSkills;
    }
    public void setEmployeeSkills(Set<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }
    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
