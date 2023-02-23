package GaonNuri.Project.ShoppingMall.order.repository;

import GaonNuri.Project.ShoppingMall.order.data.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //구매 이력
    @Query("select o from Order o " + "where o.member.email = :email " + "order by o.orderDate desc")
    List<Order> findOrders(@Param("email") String email, Pageable pageable);

    //주문 개수
    @Query("select  count(o) from Order o " + "where o.member.email = :email")
    Long countOrder(@Param("email") String email);

    @Query("select o from Order o " + "order by o.orderDate desc")
    List<Order> findAllOrders(Pageable pageable);
}
