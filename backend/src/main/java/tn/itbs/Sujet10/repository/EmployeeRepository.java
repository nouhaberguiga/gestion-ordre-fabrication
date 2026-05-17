package tn.itbs.Sujet10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.Sujet10.entity.Employee;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByMachineId(Long machineId);
}
