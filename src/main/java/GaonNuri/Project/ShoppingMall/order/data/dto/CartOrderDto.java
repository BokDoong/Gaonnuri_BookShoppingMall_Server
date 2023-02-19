package GaonNuri.Project.ShoppingMall.order.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartOrderDto {

    List<CartOrderDto> cartOrderDtoList;
    private Long cartItemId;

}
