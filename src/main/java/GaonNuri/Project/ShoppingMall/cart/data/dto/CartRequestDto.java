package GaonNuri.Project.ShoppingMall.cart.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDto {

    private Long itemId;

    private int count;
}
