package tn.itbs.Sujet10.service;

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

    // =========================
    // CREATE ORDER (DTO VERSION)
    // =========================
    public OrderFabrication createOrder(OrderRequestDTO request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Machine machine = machineRepository.findById(request.getMachineId())
                .orElseThrow(() -> new RuntimeException("Machine not found"));

        List<Employee> employees = employeeRepository.findAllById(request.getEmployeeIds());

        if (employees.isEmpty()) {
            throw new RuntimeException("Employees list is empty");
        }

        OrderFabrication o = new OrderFabrication();
        o.setQuantity(request.getQuantity());
        o.setProduct(product);
        o.setMachine(machine);
        o.setEmployees(employees);
        o.setStatus("CREATED");

        return orderRepository.save(o);
    }

    // =========================
    // GET ALL
    // =========================
    public List<OrderFabrication> getAll() {
        return orderRepository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public OrderFabrication getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // =========================
    // DELETE
    // =========================
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    // =========================
    // START ORDER
    // =========================
    public OrderFabrication startOrder(Long id) {

        OrderFabrication o = getById(id);

        if ("IN_PROGRESS".equals(o.getStatus())) {
            throw new RuntimeException("Order already started");
        }

        o.setStatus("IN_PROGRESS");

        Machine m = o.getMachine();
        m.setStatus("BUSY");

        return orderRepository.save(o);
    }

    // =========================
    // FINISH ORDER
    // =========================
    public OrderFabrication finishOrder(Long id) {

        OrderFabrication o = getById(id);

        if (!"IN_PROGRESS".equals(o.getStatus())) {
            throw new RuntimeException("Order must be IN_PROGRESS to finish");
        }

        o.setStatus("DONE");

        Machine m = o.getMachine();
        m.setStatus("AVAILABLE");

        return orderRepository.save(o);
    }
}