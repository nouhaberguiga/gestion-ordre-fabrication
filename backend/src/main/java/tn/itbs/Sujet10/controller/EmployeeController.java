package tn.itbs.Sujet10.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.itbs.Sujet10.entity.Employee;
import tn.itbs.Sujet10.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // CREATE (avec validation)
    @PostMapping
    public Employee create(@Valid @RequestBody Employee e) {
        return employeeService.save(e);
    }

    // GET ALL
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee e) {
        return employeeService.update(id, e);
    }

    // SET AVAILABLE
    @PutMapping("/available/{id}")
    public Employee setAvailable(@PathVariable Long id) {
        return employeeService.setAvailable(id);
    }

    // SET UNAVAILABLE
    @PutMapping("/unavailable/{id}")
    public Employee setUnavailable(@PathVariable Long id) {
        return employeeService.setUnavailable(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}