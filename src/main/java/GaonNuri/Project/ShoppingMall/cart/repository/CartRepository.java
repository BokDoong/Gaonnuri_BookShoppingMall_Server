package GaonNuri.Project.ShoppingMall.cart.repository;

import GaonNuri.Project.ShoppingMall.cart.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByMemberId(Long memberId);

}
