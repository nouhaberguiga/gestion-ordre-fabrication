package tn.itbs.Sujet10.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.Sujet10.entity.Employee;
import tn.itbs.Sujet10.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee e) {
        return employeeRepository.save(e);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // ✅ UPDATE ajouté
    public Employee update(Long id, Employee e) {
        Employee existing = employeeRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setFirstName(e.getFirstName());
            existing.setLastName(e.getLastName());
            existing.setRole(e.getRole());
            existing.setAvailable(e.isAvailable());

            return employeeRepository.save(existing);
        }

        return null;
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    // logique métier
    public Employee setAvailable(Long id) {
        Employee e = employeeRepository.findById(id).orElse(null);
        if (e != null) {
            e.setAvailable(true);
            return employeeRepository.save(e);
        }
        return null;
    }

    public Employee setUnavailable(Long id) {
        Employee e = employeeRepository.findById(id).orElse(null);
        if (e != null) {
            e.setAvailable(false);
            return employeeRepository.save(e);
        }
        return null;
    }
}