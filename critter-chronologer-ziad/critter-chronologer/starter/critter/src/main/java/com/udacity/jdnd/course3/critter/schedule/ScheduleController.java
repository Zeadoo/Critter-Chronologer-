package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        List<Long> employeeIds = schedule.getEmployee().stream().map(Employee::getEmployeeId).collect(Collectors.toList());
        List<Long> petIds = schedule.getPets().stream().map(Pet::getPetId).collect(Collectors.toList());

        return new ScheduleDTO(schedule.getScheduleId(), employeeIds, petIds, schedule.getScheduleDate(), schedule.getScheduleActivities());
    }
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule(scheduleDTO.getDate(), scheduleDTO.getActivities());;
        ScheduleDTO converteSchedule;
        try {
            converteSchedule = convertScheduleToScheduleDTO(scheduleService.saveSchedule(schedule, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds()));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule could not be saved", exception);
        }
        return converteSchedule;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedules().stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedule;
        try {
            schedule = scheduleService.getPetSchedule(petId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet schedule with id: " + petId + " not found", exception);
        }
        return schedule.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedule;
        try {
            schedule = scheduleService.getEmployeeSchedule(employeeId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee schedule with employee id: " + employeeId + " not found", exception);
        }
        return schedule.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedule;
        try {
            schedule = scheduleService.getCustomerSchedule(customerId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule with owner id " + customerId + " not found", exception);
        }
        return schedule.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }
}
