package GaonNuri.Project.ShoppingMall.cart.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {

    private Long cartItemsId;
    private String itemName;
    private int price;
    private int count;
    private String imageUrl;

    public CartResponseDto(Long cartItemsId, String itemName, int price, int count, String imageUrl) {
        this.cartItemsId = cartItemsId;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.imageUrl = imageUrl;
    }
}
