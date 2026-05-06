package tn.itbs.Sujet10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.itbs.Sujet10.entity.Employee;
import tn.itbs.Sujet10.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // CREATE
    @PostMapping
    public Employee create(@RequestBody Employee e) {
        return employeeService.save(e);
    }

    // GET ALL
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    // AVAILABLE
    @PutMapping("/available/{id}")
    public Employee setAvailable(@PathVariable Long id) {
        return employeeService.setAvailable(id);
    }

    // UNAVAILABLE
    @PutMapping("/unavailable/{id}")
    public Employee setUnavailable(@PathVariable Long id) {
        return employeeService.setUnavailable(id);
    }
}