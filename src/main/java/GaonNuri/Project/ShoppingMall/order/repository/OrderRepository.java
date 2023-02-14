package GaonNuri.Project.ShoppingMall.order.repository;

import GaonNuri.Project.ShoppingMall.order.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
