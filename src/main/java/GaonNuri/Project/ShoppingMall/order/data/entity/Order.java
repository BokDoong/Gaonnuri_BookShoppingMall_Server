package GaonNuri.Project.ShoppingMall.order.data.entity;

import GaonNuri.Project.ShoppingMall.config.entity.BaseTimeEntity;
import GaonNuri.Project.ShoppingMall.order.data.enums.OrderStatus;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name="orders")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private long id;


    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //order_item 테이블의 order 필드에 매핑 + cascade(ALL) : Order 이 저장될 때, OrderItem 이 같이 저장됨.
    //고아객체 제거 - 부모 엔티티와 연관관계가 끊길 때 삭제
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
}
