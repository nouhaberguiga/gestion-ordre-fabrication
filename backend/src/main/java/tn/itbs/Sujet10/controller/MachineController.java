package tn.itbs.Sujet10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.itbs.Sujet10.entity.Machine;
import tn.itbs.Sujet10.service.MachineService;

@RestController
@RequestMapping("/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;

    // CREATE
    @PostMapping
    public Machine create(@RequestBody Machine m) {
        return machineService.save(m);
    }

    // GET ALL
    @GetMapping
    public List<Machine> getAll() {
        return machineService.getAll();
    }

    // AVAILABLE
    @PutMapping("/available/{id}")
    public Machine setAvailable(@PathVariable Long id) {
        return machineService.setAvailable(id);
    }

    // MAINTENANCE
    @PutMapping("/maintenance/{id}")
    public Machine setMaintenance(@PathVariable Long id) {
        return machineService.setMaintenance(id);
    }
}