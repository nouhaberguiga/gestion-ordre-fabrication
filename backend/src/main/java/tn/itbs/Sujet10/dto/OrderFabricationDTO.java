package tn.itbs.Sujet10.dto;

import tn.itbs.Sujet10.entity.OrderStatus;
import java.time.LocalDate;
import java.util.List;

public class OrderFabricationDTO {

    private Long id;
    private String project;
    private int quantity;
    private LocalDate date;
    private OrderStatus status;
    private Long productId;
    private String productName;
    private Long machineId;
    private String machineName;
    private List<Long> employeeIds;

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Long getMachineId() { return machineId; }
    public void setMachineId(Long machineId) { this.machineId = machineId; }

    public String getMachineName() { return machineName; }
    public void setMachineName(String machineName) { this.machineName = machineName; }

    public List<Long> getEmployeeIds() { return employeeIds; }
    public void setEmployeeIds(List<Long> employeeIds) { this.employeeIds = employeeIds; }
}