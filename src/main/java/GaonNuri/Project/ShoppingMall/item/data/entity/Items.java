package GaonNuri.Project.ShoppingMall.item.data.entity;

import GaonNuri.Project.ShoppingMall.config.entity.BaseTimeEntity;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name="products")
public class Items extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "item_id")
    private long id;

    @Column(name= "item_name", nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    //재고
    @Column(nullable = false)
    private int stockNumber;

    //판매상태
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    //이미지 url
    @Column
    private String imageUrl;
}
