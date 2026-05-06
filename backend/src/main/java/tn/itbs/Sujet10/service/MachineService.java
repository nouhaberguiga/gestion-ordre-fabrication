package tn.itbs.Sujet10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.Sujet10.entity.Machine;
import tn.itbs.Sujet10.repository.MachineRepository;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    // CREATE / UPDATE
    public Machine save(Machine m) {
        return machineRepository.save(m);
    }

    // GET ALL
    public List<Machine> getAll() {
        return machineRepository.findAll();
    }

    // GET BY ID
    public Machine getById(Long id) {
        return machineRepository.findById(id).orElse(null);
    }

    // DELETE
    public void delete(Long id) {
        machineRepository.deleteById(id);
    }

    // LOGIQUE MÉTIER SIMPLE
    public Machine setAvailable(Long id) {
        Machine m = machineRepository.findById(id).orElse(null);
        if (m != null) {
            m.setStatus("AVAILABLE");
            return machineRepository.save(m);
        }
        return null;
    }

    public Machine setMaintenance(Long id) {
        Machine m = machineRepository.findById(id).orElse(null);
        if (m != null) {
            m.setStatus("MAINTENANCE");
            return machineRepository.save(m);
        }
        return null;
    }
}