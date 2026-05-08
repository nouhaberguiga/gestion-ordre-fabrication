package tn.itbs.Sujet10.dto;

import jakarta.validation.constraints.NotBlank;
import tn.itbs.Sujet10.entity.MachineStatus;
import java.time.LocalDate;

public class MachineDTO {

    private Long id;

    @NotBlank(message = "Machine name is required")
    private String name;

    private MachineStatus status;

    private LocalDate lastMaintenance;

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public MachineStatus getStatus() { return status; }
    public void setStatus(MachineStatus status) { this.status = status; }

    public LocalDate getLastMaintenance() { return lastMaintenance; }
    public void setLastMaintenance(LocalDate lastMaintenance) { this.lastMaintenance = lastMaintenance; }
}