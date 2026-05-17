package tn.itbs.Sujet10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.Sujet10.entity.OrderFabrication;
import tn.itbs.Sujet10.entity.OrderStatus;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderFabrication, Long> {
    List<OrderFabrication> findByStatus(OrderStatus status);
    List<OrderFabrication> findByProductId(Long productId);
    List<OrderFabrication> findByMachineId(Long machineId);
    List<OrderFabrication> findByEmployeesId(Long employeeId);
}
