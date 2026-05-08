package tn.itbs.Sujet10.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Machine name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    private MachineStatus status = MachineStatus.AVAILABLE;

    private LocalDate lastMaintenance;

    @OneToMany(mappedBy = "machine")
    private List<Employee> employees;

    @JsonManagedReference
    @OneToMany(mappedBy = "machine")
    private List<OrderFabrication> orders;

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public MachineStatus getStatus() { return status; }
    public void setStatus(MachineStatus status) { this.status = status; }

    public LocalDate getLastMaintenance() { return lastMaintenance; }
    public void setLastMaintenance(LocalDate lastMaintenance) { this.lastMaintenance = lastMaintenance; }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }

    public List<OrderFabrication> getOrders() { return orders; }
    public void setOrders(List<OrderFabrication> orders) { this.orders = orders; }
}