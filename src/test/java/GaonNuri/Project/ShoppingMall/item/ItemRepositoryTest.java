package GaonNuri.Project.ShoppingMall.item;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.repository.inter.ItemsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemsRepository itemsRepository;

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

}
