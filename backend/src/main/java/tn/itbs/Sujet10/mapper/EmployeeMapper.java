package tn.itbs.Sujet10.mapper;

import org.springframework.stereotype.Component;
import tn.itbs.Sujet10.dto.EmployeeDTO;
import tn.itbs.Sujet10.entity.Employee;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) return null;
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setRole(employee.getRole());
        dto.setAvailable(employee.isAvailable());
        if (employee.getMachine() != null) {
            dto.setMachineId(employee.getMachine().getId());
            dto.setMachineName(employee.getMachine().getName());
        }
        return dto;
    }

    public Employee fromDto(EmployeeDTO dto) {
        if (dto == null) return null;
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setRole(dto.getRole());
        employee.setAvailable(dto.isAvailable());
        return employee;
    }

    public List<EmployeeDTO> toListDto(List<Employee> employees) {
        return employees.stream().map(this::toDto).collect(Collectors.toList());
    }
}
