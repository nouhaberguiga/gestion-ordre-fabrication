package tn.itbs.Sujet10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.Sujet10.entity.Machine;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}