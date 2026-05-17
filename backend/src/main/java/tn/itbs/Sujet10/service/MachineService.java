package tn.itbs.Sujet10.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.itbs.Sujet10.entity.Machine;
import tn.itbs.Sujet10.entity.MachineStatus;
import tn.itbs.Sujet10.entity.Employee;
import tn.itbs.Sujet10.entity.OrderFabrication;
import tn.itbs.Sujet10.repository.EmployeeRepository;
import tn.itbs.Sujet10.repository.MachineRepository;
import tn.itbs.Sujet10.repository.OrderRepository;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Machine save(Machine m) {
        if (m.getStatus() == null) {
            m.setStatus(MachineStatus.AVAILABLE);
        }
        return machineRepository.save(m);
    }

    public List<Machine> getAll() {
        return machineRepository.findAll();
    }

    public Machine getById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine not found with id: " + id));
    }

    public Machine update(Long id, Machine m) {
        Machine existing = getById(id);
        existing.setName(m.getName());
        existing.setStatus(m.getStatus());
        existing.setLastMaintenance(m.getLastMaintenance());
        return machineRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        if (!machineRepository.existsById(id)) {
            throw new RuntimeException("Machine not found with id: " + id);
        }

        List<OrderFabrication> orders = orderRepository.findByMachineId(id);
        for (OrderFabrication order : orders) {
            order.getEmployees().clear();
        }
        orderRepository.deleteAll(orders);

        List<Employee> employees = employeeRepository.findByMachineId(id);
        for (Employee employee : employees) {
            employee.setMachine(null);
        }
        employeeRepository.saveAll(employees);

        machineRepository.deleteById(id);
    }

    public Machine setAvailable(Long id) {
        Machine m = getById(id);
        m.setStatus(MachineStatus.AVAILABLE);
        return machineRepository.save(m);
    }

    public Machine setMaintenance(Long id) {
        Machine m = getById(id);
        m.setStatus(MachineStatus.MAINTENANCE);
        m.setLastMaintenance(LocalDate.now());
        return machineRepository.save(m);
    }

    public Machine setInUse(Long id) {
        Machine m = getById(id);
        m.setStatus(MachineStatus.IN_USE);
        return machineRepository.save(m);
    }

    public Machine setBroken(Long id) {
        Machine m = getById(id);
        m.setStatus(MachineStatus.BROKEN);
        return machineRepository.save(m);
    }
}
