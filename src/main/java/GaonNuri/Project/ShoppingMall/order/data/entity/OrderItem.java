package GaonNuri.Project.ShoppingMall.order.data.entity;

import GaonNuri.Project.ShoppingMall.config.entity.BaseTimeEntity;
import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
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

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Items items;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

}
