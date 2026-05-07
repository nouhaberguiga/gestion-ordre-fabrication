package tn.itbs.Sujet10.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.itbs.Sujet10.entity.Machine;
import tn.itbs.Sujet10.service.MachineService;

@RestController
@RequestMapping("/api/machines")
@CrossOrigin("*")
public class MachineController {

    @Autowired
    private MachineService machineService;

    // =========================
    // CREATE MACHINE
    // =========================
    @PostMapping
    public Machine create(@Valid @RequestBody Machine m) {
        return machineService.save(m);
    }

    // =========================
    // GET ALL MACHINES
    // =========================
    @GetMapping
    public List<Machine> getAll() {
        return machineService.getAll();
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public Machine getById(@PathVariable Long id) {
        return machineService.getById(id);
    }

    // =========================
    // SET AVAILABLE
    // =========================
    @PutMapping("/available/{id}")
    public Machine setAvailable(@PathVariable Long id) {
        return machineService.setAvailable(id);
    }

    // =========================
    // SET MAINTENANCE
    // =========================
    @PutMapping("/maintenance/{id}")
    public Machine setMaintenance(@PathVariable Long id) {
        return machineService.setMaintenance(id);
    }

    // =========================
    // SET IN USE (OPTIONNEL MAIS TRÈS UTILE)
    // =========================
    @PutMapping("/in-use/{id}")
    public Machine setInUse(@PathVariable Long id) {
        return machineService.setInUse(id);
    }

    // =========================
    // DELETE MACHINE
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        machineService.delete(id);
    }
}