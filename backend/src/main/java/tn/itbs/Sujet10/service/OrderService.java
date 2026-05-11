package tn.itbs.Sujet10.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.Sujet10.dto.OrderRequestDTO;
import tn.itbs.Sujet10.entity.*;
import tn.itbs.Sujet10.repository.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // CREATE ORDER
    public OrderFabrication createOrder(OrderRequestDTO request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Machine machine = machineRepository.findById(request.getMachineId())
                .orElseThrow(() -> new RuntimeException("Machine not found"));

        if (machine.getStatus() != MachineStatus.AVAILABLE) {
            throw new RuntimeException("Machine is not available (status: " + machine.getStatus() + ")");
        }

        List<Employee> employees = employeeRepository.findAllById(request.getEmployeeIds());
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found for provided IDs");
        }

        OrderFabrication order = new OrderFabrication();
        order.setProject(request.getProject());
        order.setQuantity(request.getQuantity());
        order.setDate(LocalDate.now());
        order.setProduct(product);
        order.setMachine(machine);
        order.setEmployees(employees);
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
    }

    // GET ALL
    public List<OrderFabrication> getAll() {
        return orderRepository.findAll();
    }

    // GET BY ID
    public OrderFabrication getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    // DELETE
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    // START ORDER
    public OrderFabrication startOrder(Long id) {
        OrderFabrication order = getById(id);

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Cannot start order — current status: " + order.getStatus());
        }

        Machine machine = order.getMachine();
        machine.setStatus(MachineStatus.IN_USE);
        machineRepository.save(machine);

        order.setStatus(OrderStatus.IN_PROGRESS);
        return orderRepository.save(order);
    }

    // FINISH ORDER
    public OrderFabrication finishOrder(Long id) {
        OrderFabrication order = getById(id);

        if (order.getStatus() != OrderStatus.IN_PROGRESS
                && order.getStatus() != OrderStatus.ON_HOLD) {
            throw new RuntimeException("Cannot finish order — current status: " + order.getStatus());
        }

        Machine machine = order.getMachine();
        machine.setStatus(MachineStatus.AVAILABLE);
        machineRepository.save(machine);

        order.setStatus(OrderStatus.COMPLETED);
        return orderRepository.save(order);
    }

    // HOLD ORDER
    public OrderFabrication holdOrder(Long id) {
        OrderFabrication order = getById(id);

        if (order.getStatus() != OrderStatus.IN_PROGRESS) {
            throw new RuntimeException("Cannot put order on hold — current status: " + order.getStatus());
        }

        Machine machine = order.getMachine();
        machine.setStatus(MachineStatus.AVAILABLE);
        machineRepository.save(machine);

        order.setStatus(OrderStatus.ON_HOLD);
        return orderRepository.save(order);
    }

    // RESUME ORDER
    public OrderFabrication resumeOrder(Long id) {
        OrderFabrication order = getById(id);

        if (order.getStatus() != OrderStatus.ON_HOLD) {
            throw new RuntimeException("Cannot resume order — current status: " + order.getStatus());
        }

        Machine machine = order.getMachine();

        if (machine.getStatus() != MachineStatus.AVAILABLE) {
            throw new RuntimeException("Machine is not available (status: " + machine.getStatus() + ")");
        }

        machine.setStatus(MachineStatus.IN_USE);
        machineRepository.save(machine);

        order.setStatus(OrderStatus.IN_PROGRESS);
        return orderRepository.save(order);
    }

    // UPDATE STATUS (SIMPLIFIÉ ET CORRIGÉ)
    public OrderFabrication updateStatus(Long id, OrderStatus status) {

        OrderFabrication order = getById(id);
        Machine machine = order.getMachine();

        if (status == OrderStatus.IN_PROGRESS) {

            if (machine.getStatus() != MachineStatus.AVAILABLE) {
                throw new RuntimeException("Machine is not available (status: " + machine.getStatus() + ")");
            }

            machine.setStatus(MachineStatus.IN_USE);
        }

        else if (status == OrderStatus.COMPLETED
                || status == OrderStatus.CANCELLED
                || status == OrderStatus.ON_HOLD) {

            machine.setStatus(MachineStatus.AVAILABLE);
        }

        machineRepository.save(machine);
        order.setStatus(status);

        return orderRepository.save(order);
    }

    // CANCEL ORDER
    public OrderFabrication cancelOrder(Long id) {

        OrderFabrication order = getById(id);

        if (order.getStatus() == OrderStatus.COMPLETED) {
            throw new RuntimeException("Cannot cancel a completed order");
        }

        if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            Machine machine = order.getMachine();
            machine.setStatus(MachineStatus.AVAILABLE);
            machineRepository.save(machine);
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}