package tn.itbs.Sujet10.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class OrderFabrication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    // 🔥 STATUS PROPRE AVEC ENUM
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 🔥 RELATION PRODUCT
    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Product is required")
    private Product product;

    // 🔥 RELATION MACHINE (UNE SEULE FOIS)
    @ManyToOne
    @JoinColumn(name = "machine_id")
    @NotNull(message = "Machine is required")
    private Machine machine;

    // 🔥 EMPLOYEES
    @ManyToMany
    @NotNull(message = "Employees are required")
    private List<Employee> employees;

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Product getProduct() {
        return product;
    }

    public Machine getMachine() {
        return machine;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}