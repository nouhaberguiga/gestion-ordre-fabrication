package tn.itbs.Sujet10.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.itbs.Sujet10.entity.Employee;
import tn.itbs.Sujet10.service.EmployeeService;
import tn.itbs.Sujet10.dto.EmployeeDTO;
import tn.itbs.Sujet10.mapper.EmployeeMapper;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    // CREATE (avec validation)
    @PostMapping
    public EmployeeDTO create(@Valid @RequestBody EmployeeDTO dto) {
        Employee employee = employeeMapper.fromDto(dto);
        Employee saved = employeeService.saveWithMachine(employee, dto.getMachineId());
        return employeeMapper.toDto(saved);
    }

    // GET ALL
    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeMapper.toListDto(employeeService.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable Long id) {
        return employeeMapper.toDto(employeeService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {
        Employee employee = employeeMapper.fromDto(dto);
        Employee updated = employeeService.updateWithMachine(id, employee, dto.getMachineId());
        return employeeMapper.toDto(updated);
    }

    // SET AVAILABLE
    @PutMapping("/available/{id}")
    public EmployeeDTO setAvailable(@PathVariable Long id) {
        return employeeMapper.toDto(employeeService.setAvailable(id));
    }

    // SET UNAVAILABLE
    @PutMapping("/unavailable/{id}")
    public EmployeeDTO setUnavailable(@PathVariable Long id) {
        return employeeMapper.toDto(employeeService.setUnavailable(id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
