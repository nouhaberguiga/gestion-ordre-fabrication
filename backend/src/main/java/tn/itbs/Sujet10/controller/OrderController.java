package tn.itbs.Sujet10.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.itbs.Sujet10.dto.OrderFabricationDTO;
import tn.itbs.Sujet10.dto.OrderRequestDTO;
import tn.itbs.Sujet10.mapper.OrderMapper;
import tn.itbs.Sujet10.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public OrderFabricationDTO create(@Valid @RequestBody OrderRequestDTO request) {
        return orderMapper.toDto(orderService.createOrder(request));
    }

    @GetMapping
    public List<OrderFabricationDTO> getAll() {
        return orderMapper.toListDto(orderService.getAll());
    }

    @GetMapping("/{id}")
    public OrderFabricationDTO getById(@PathVariable Long id) {
        return orderMapper.toDto(orderService.getById(id));
    }

    @PutMapping("/start/{id}")
    public OrderFabricationDTO start(@PathVariable Long id) {
        return orderMapper.toDto(orderService.startOrder(id));
    }

    @PutMapping("/finish/{id}")
    public OrderFabricationDTO finish(@PathVariable Long id) {
        return orderMapper.toDto(orderService.finishOrder(id));
    }

    @PutMapping("/cancel/{id}")
    public OrderFabricationDTO cancel(@PathVariable Long id) {
        return orderMapper.toDto(orderService.cancelOrder(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}