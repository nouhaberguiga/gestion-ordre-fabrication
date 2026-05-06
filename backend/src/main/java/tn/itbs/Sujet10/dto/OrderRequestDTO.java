package tn.itbs.Sujet10.dto;

import java.util.List;

import jakarta.validation.constraints.*;

public class OrderRequestDTO {

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }
}