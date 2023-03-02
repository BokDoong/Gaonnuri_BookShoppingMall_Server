package GaonNuri.Project.ShoppingMall.cart.data.entity;

import GaonNuri.Project.ShoppingMall.config.auditing.BaseTimeEntity;
import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="cart_item")
@Getter @Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CartItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Items items;

    private int count;

    public static CartItem creatCartItem(Cart cart, Items items, int count) {
        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setItems(items);
        cartItem.setCount(count);

        return cartItem;
    }

    //장바구니의 아이템을 또 장바구니로 담았을때 수량 증가
    public void addCount(int count) {
        this.count += count;
    }

    //장바구니 수정
    public void updateCount(int count){
        this.count = count;
    }
}
