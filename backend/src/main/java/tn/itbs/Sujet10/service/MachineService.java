package tn.itbs.Sujet10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.Sujet10.entity.Machine;
import tn.itbs.Sujet10.entity.MachineStatus;
import tn.itbs.Sujet10.repository.MachineRepository;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    // =========================
    // CREATE / UPDATE
    // =========================
    public Machine save(Machine m) {
        return machineRepository.save(m);
    }

    // =========================
    // GET ALL
    // =========================
    public List<Machine> getAll() {
        return machineRepository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public Machine getById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine not found"));
    }

    // =========================
    // DELETE
    // =========================
    public void delete(Long id) {
        if (!machineRepository.existsById(id)) {
            throw new RuntimeException("Machine not found");
        }
        machineRepository.deleteById(id);
    }

    // =========================
    // SET AVAILABLE
    // =========================
    public Machine setAvailable(Long id) {
        Machine m = getById(id);
        m.setStatus(MachineStatus.AVAILABLE);
        return machineRepository.save(m);
    }

    // =========================
    // SET MAINTENANCE
    // =========================
    public Machine setMaintenance(Long id) {
        Machine m = getById(id);
        m.setStatus(MachineStatus.MAINTENANCE);
        return machineRepository.save(m);
    }

    // =========================
    // SET IN USE (OPTIONNEL mais utile)
    // =========================
    public Machine setInUse(Long id) {
        Machine m = getById(id);
        m.setStatus(MachineStatus.IN_USE);
        return machineRepository.save(m);
    }
}