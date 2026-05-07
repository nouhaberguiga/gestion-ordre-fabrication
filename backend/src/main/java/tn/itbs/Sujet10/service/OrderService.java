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
    // CREATE ORDER
    // =========================
    public OrderFabrication createOrder(OrderRequestDTO request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Machine machine = machineRepository.findById(request.getMachineId())
                .orElseThrow(() -> new RuntimeException("Machine not found"));

        // 🔥 CHECK MACHINE AVAILABLE
        if (machine.getStatus() != MachineStatus.AVAILABLE) {
            throw new RuntimeException("Machine not available");
        }

        List<Employee> employees = employeeRepository.findAllById(request.getEmployeeIds());

        if (employees.isEmpty()) {
            throw new RuntimeException("Employees list is empty");
        }

        OrderFabrication order = new OrderFabrication();
        order.setQuantity(request.getQuantity());
        order.setProduct(product);
        order.setMachine(machine);
        order.setEmployees(employees);
        order.setStatus(OrderStatus.PENDING);

        return orderRepository.save(order);
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

        OrderFabrication order = getById(id);

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Order already started or finished");
        }

        Machine machine = order.getMachine();

        // 🔥 MACHINE OCCUPÉE
        machine.setStatus(MachineStatus.IN_USE);

        order.setStatus(OrderStatus.IN_PROGRESS);

        machineRepository.save(machine);
        return orderRepository.save(order);
    }

    // =========================
    // FINISH ORDER
    // =========================
    public OrderFabrication finishOrder(Long id) {

        OrderFabrication order = getById(id);

        if (order.getStatus() != OrderStatus.IN_PROGRESS) {
            throw new RuntimeException("Order not started");
        }

        Machine machine = order.getMachine();

        // 🔥 MACHINE LIBRE
        machine.setStatus(MachineStatus.AVAILABLE);

        order.setStatus(OrderStatus.FINISHED);

        machineRepository.save(machine);
        return orderRepository.save(order);
    }
}