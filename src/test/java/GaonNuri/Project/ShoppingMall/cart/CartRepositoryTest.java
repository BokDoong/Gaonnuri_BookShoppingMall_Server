package GaonNuri.Project.ShoppingMall.cart;

import GaonNuri.Project.ShoppingMall.cart.data.entity.Cart;
import GaonNuri.Project.ShoppingMall.cart.repository.CartRepository;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import GaonNuri.Project.ShoppingMall.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
public class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    public void 장바구니_회원_매핑테스트(){

        Member member = Member.builder()
                .email("ctce7226@gmail.com")
                .password("lee~2371765")
                .name("이진")
                .build();
        this.memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        Cart savedcart = cartRepository.getById(cart.getId());
        Assertions.assertEquals(savedcart.getMember().getId(), member.getId());
    }

}
