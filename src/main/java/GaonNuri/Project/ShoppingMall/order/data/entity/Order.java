package GaonNuri.Project.ShoppingMall.order.data.entity;

import GaonNuri.Project.ShoppingMall.config.entity.BaseTimeEntity;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import GaonNuri.Project.ShoppingMall.order.data.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    //주문자
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    //주문 시간
    private LocalDateTime orderDate;

    //주문 가격
    private int orderPrice;

    //주문 상태
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //order_item 테이블의 order 필드에 매핑 + cascade(ALL) : Order 이 저장될 때, OrderItem 이 같이 저장됨.
    //고아객체 제거 - 부모 엔티티와 연관관계가 끊길 때 삭제
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    //주문 상품 추가 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);  //주문 객체에 주문상품 연결
        orderItem.setOrder(this);   //주문 상품 객체에 주문객체 연결
    }

    //주문 생성 메서드
    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);
        for(OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }
        order.setOrderPrice(order.getTotalPrice());
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    //주문 가격
    public int getTotalPrice() {
        int totalPrice = 0;

        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

}
