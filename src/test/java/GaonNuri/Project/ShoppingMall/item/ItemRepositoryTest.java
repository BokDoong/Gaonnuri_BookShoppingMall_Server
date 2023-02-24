package GaonNuri.Project.ShoppingMall.item;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.repository.ItemsRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void 상품_조회() {
        //given
        Items items = Items.builder()
                .itemName("책")
                .price(1000)
                .itemStatus(ItemStatus.SOLD_OUT)
                .itemDetail("논리회로 전공책입니다.")
                .build();

        //when
        Items savedItem = itemsRepository.save(items);

        //then
        Assertions.assertThat(savedItem.getItemName())
                .isEqualTo(items.getItemName());
    }

    @Test
    @Transactional
    void 상품_생성(){
        for (int i = 0; i < 7; i++) {
            Items items = Items.builder()
                    .itemName("물품 " + String.valueOf(i))
                    .price(i*1000)
                    .itemDetail(String.valueOf(i) + "번째")
                    .itemStatus(ItemStatus.SOLD_OUT)
                    .stockNumber(15)
                    .build();
            itemsRepository.save(items);
        }
        for (int i = 8; i < 20; i++) {
            Items items = Items.builder()
                    .itemName("물품 " + String.valueOf(i))
                    .price(i*1000)
                    .itemDetail(String.valueOf(i) + "번째")
                    .itemStatus(ItemStatus.FOR_SALE)
                    .stockNumber(20)
                    .build();
            itemsRepository.save(items);
        }
    }

}
