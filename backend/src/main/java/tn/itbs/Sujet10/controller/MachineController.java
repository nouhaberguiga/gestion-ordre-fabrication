package tn.itbs.Sujet10.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.itbs.Sujet10.dto.MachineDTO;
import tn.itbs.Sujet10.entity.Machine;
import tn.itbs.Sujet10.mapper.MachineMapper;
import tn.itbs.Sujet10.service.MachineService;

@RestController
@RequestMapping("/api/machines")
@CrossOrigin("*")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Autowired
    private MachineMapper machineMapper;

    @PostMapping
    public MachineDTO create(@Valid @RequestBody Machine m) {
        return machineMapper.toDto(machineService.save(m));
    }

    @GetMapping
    public List<MachineDTO> getAll() {
        return machineMapper.toListDto(machineService.getAll());
    }

    @GetMapping("/{id}")
    public MachineDTO getById(@PathVariable Long id) {
        return machineMapper.toDto(machineService.getById(id));
    }

    @PutMapping("/{id}")
    public MachineDTO update(@PathVariable Long id, @Valid @RequestBody Machine m) {
        return machineMapper.toDto(machineService.update(id, m));
    }

    @PutMapping("/available/{id}")
    public MachineDTO setAvailable(@PathVariable Long id) {
        return machineMapper.toDto(machineService.setAvailable(id));
    }

    @PutMapping("/maintenance/{id}")
    public MachineDTO setMaintenance(@PathVariable Long id) {
        return machineMapper.toDto(machineService.setMaintenance(id));
    }

    @PutMapping("/in-use/{id}")
    public MachineDTO setInUse(@PathVariable Long id) {
        return machineMapper.toDto(machineService.setInUse(id));
    }

    @PutMapping("/broken/{id}")
    public MachineDTO setBroken(@PathVariable Long id) {
        return machineMapper.toDto(machineService.setBroken(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        machineService.delete(id);
    }
}