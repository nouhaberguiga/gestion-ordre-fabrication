package tn.itbs.Sujet10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public OrderFabrication createOrder(OrderFabrication o,
                                        Long productId,
                                        Long machineId,
                                        List<Long> employeeIds) {

        Product product = productRepository.findById(productId).orElse(null);
        Machine machine = machineRepository.findById(machineId).orElse(null);
        List<Employee> employees = employeeRepository.findAllById(employeeIds);

        o.setProduct(product);
        o.setMachine(machine);
        o.setEmployees(employees);

        o.setStatus("CREATED");

        return orderRepository.save(o);
    }

    public List<OrderFabrication> getAll() {
        return orderRepository.findAll();
    }

    public OrderFabrication startOrder(Long id) {
        OrderFabrication o = orderRepository.findById(id).orElse(null);

        if (o != null) {
            o.setStatus("IN_PROGRESS");

            Machine m = o.getMachine();
            m.setStatus("BUSY");

            return orderRepository.save(o);
        }

        return null;
    }

    public OrderFabrication finishOrder(Long id) {
        OrderFabrication o = orderRepository.findById(id).orElse(null);

        if (o != null) {
            o.setStatus("DONE");

            Machine m = o.getMachine();
            m.setStatus("AVAILABLE");

            return orderRepository.save(o);
        }

        return null;
    }
}