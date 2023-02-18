package GaonNuri.Project.ShoppingMall.order.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {

    //해당 상품 id
    private Long itemId;

    //상품 주문 수량
    private int count;
}
