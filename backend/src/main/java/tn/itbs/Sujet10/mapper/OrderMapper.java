package tn.itbs.Sujet10.mapper;

import org.springframework.stereotype.Component;
import tn.itbs.Sujet10.dto.OrderFabricationDTO;
import tn.itbs.Sujet10.entity.Employee;
import tn.itbs.Sujet10.entity.OrderFabrication;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderFabricationDTO toDto(OrderFabrication order) {
        if (order == null) return null;
        OrderFabricationDTO dto = new OrderFabricationDTO();
        dto.setId(order.getId());
        dto.setProject(order.getProject());
        dto.setQuantity(order.getQuantity());
        dto.setDate(order.getDate());
        dto.setStatus(order.getStatus());

        if (order.getProduct() != null) {
            dto.setProductId(order.getProduct().getId());
            dto.setProductName(order.getProduct().getName());
        }
        if (order.getMachine() != null) {
            dto.setMachineId(order.getMachine().getId());
            dto.setMachineName(order.getMachine().getName());
        }
        if (order.getEmployees() != null) {
            dto.setEmployeeIds(
                order.getEmployees().stream()
                    .map(Employee::getId)
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public List<OrderFabricationDTO> toListDto(List<OrderFabrication> orders) {
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }
}