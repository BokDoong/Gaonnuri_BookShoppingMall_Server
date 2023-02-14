package GaonNuri.Project.ShoppingMall.order;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.repository.ItemsRepository;
import GaonNuri.Project.ShoppingMall.order.data.entity.Order;
import GaonNuri.Project.ShoppingMall.order.data.entity.OrderItem;
import GaonNuri.Project.ShoppingMall.order.repository.OrderRepository;
import GaonNuri.Project.ShoppingMall.user.repository.AuthorityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class OrderTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    ItemsRepository itemsRepository;

    @PersistenceContext
    EntityManager em;

    public Items createItem(){

        Items items = Items.builder()
                .itemName("책")
                .price(1000)
                .itemStatus(ItemStatus.SOLD_OUT)
                .itemDetail("논리회로 전공책입니다.")
                .build();

        return items;
    }

    @Test
    public void 영속성_전이_테스트() {
        //given
        Order order = new Order();

        //when: 3개의 주문물품을 주문한다고 가정
        for (int i = 0; i < 3; i++) {
            //Item 생성+저장
            Items items = this.createItem();
            itemsRepository.save(items);

            OrderItem orderItem = OrderItem.builder()
                    .items(items)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();

            order.getOrderItems().add(orderItem);
        }

        //then
        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        Assertions.assertEquals(3, savedOrder.getOrderItems().size());
    }



}
