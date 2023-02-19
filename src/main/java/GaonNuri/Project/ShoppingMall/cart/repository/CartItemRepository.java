package GaonNuri.Project.ShoppingMall.cart.repository;

import GaonNuri.Project.ShoppingMall.cart.data.dto.CartResponseDto;
import GaonNuri.Project.ShoppingMall.cart.data.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemsId(Long cartId, Long itemID);

    @Query("select new GaonNuri.Project.ShoppingMall.cart.data.dto.CartResponseDto(ci.id, i.itemName, i.price, ci.count) " +
            "from CartItem ci " + "join ci.items i " + "where ci.cart.id = :cartId " + "order by ci.createdAt desc")
    List<CartResponseDto> findCartResponseDtoList(@Param("cartId") Long cartId);
}
