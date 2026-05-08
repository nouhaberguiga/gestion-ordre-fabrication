package tn.itbs.Sujet10.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class OrderRequestDTO {

    @NotBlank(message = "Project name is required")
    private String project;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Machine ID is required")
    private Long machineId;

    @NotEmpty(message = "Employee list must not be empty")
    private List<Long> employeeIds;

    // GETTERS & SETTERS

    public String getProject() { return project; }
    public void setProject(String project) { this.project = project; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Long getMachineId() { return machineId; }
    public void setMachineId(Long machineId) { this.machineId = machineId; }

    public List<Long> getEmployeeIds() { return employeeIds; }
    public void setEmployeeIds(List<Long> employeeIds) { this.employeeIds = employeeIds; }
}