package GaonNuri.Project.ShoppingMall.order.data.entity;

import GaonNuri.Project.ShoppingMall.config.entity.BaseTimeEntity;
import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "order_item")
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Items items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격
    private int count;  //주문수량

    //주문상품 생성
    public static OrderItem createOrderItems(Items items, int count){

        OrderItem orderItem = OrderItem.builder()
                .items(items)
                .count(count)
                .orderPrice(items.getPrice())
                .build();

        return orderItem;
    }

    public int getTotalPrice(){
        return orderPrice * count;
    }
}
