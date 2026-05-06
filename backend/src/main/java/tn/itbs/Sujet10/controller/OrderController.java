package tn.itbs.Sujet10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import tn.itbs.Sujet10.entity.OrderFabrication;
import tn.itbs.Sujet10.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // =========================
    // CREATE ORDER
    // =========================
    @PostMapping
    public OrderFabrication create(
            @Valid @RequestBody OrderFabrication o,

            @RequestParam @NotNull(message = "productId is required") Long productId,

            @RequestParam @NotNull(message = "machineId is required") Long machineId,

            @RequestParam @NotEmpty(message = "employeeIds list is required") List<Long> employeeIds
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
    // GET BY ID (important)
    // =========================
    @GetMapping("/{id}")
    public OrderFabrication getById(@PathVariable Long id) {
        return orderService.getById(id);
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

    // =========================
    // DELETE (bonus utile)
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}