package GaonNuri.Project.ShoppingMall.cart.data.entity;

import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public static Cart createCart(Member member) {
        Cart cart = new Cart();

        cart.member = member;
        return cart;
    }
}
