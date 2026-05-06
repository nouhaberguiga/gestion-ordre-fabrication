package tn.itbs.Sujet10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.Sujet10.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}