package tn.itbs.Sujet10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.itbs.Sujet10.entity.OrderFabrication;
import tn.itbs.Sujet10.service.OrderService;

@RestController
@RequestMapping("/api/orders") // ✅ IMPORTANT (standard REST)
@CrossOrigin("*") // (utile si Angular/React)
public class OrderController {

    @Autowired
    private OrderService orderService;

    // =========================
    // CREATE ORDER
    // =========================
    @PostMapping
    public OrderFabrication create(
            @RequestBody OrderFabrication o,
            @RequestParam Long productId,
            @RequestParam Long machineId,
            @RequestParam List<Long> employeeIds
    ) {
        return orderService.createOrder(o, productId, machineId, employeeIds);
    }

    // =========================
    // GET ALL ORDERS
    // =========================
    @GetMapping
    public List<OrderFabrication> getAll() {
        return orderService.getAll();
    }

    // =========================
    // START ORDER
    // =========================
    @PutMapping("/start/{id}")
    public OrderFabrication start(@PathVariable Long id) {
        return orderService.startOrder(id);
    }

    // =========================
    // FINISH ORDER
    // =========================
    @PutMapping("/finish/{id}")
    public OrderFabrication finish(@PathVariable Long id) {
        return orderService.finishOrder(id);
    }
}