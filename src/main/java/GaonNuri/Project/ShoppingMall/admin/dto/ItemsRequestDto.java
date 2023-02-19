package GaonNuri.Project.ShoppingMall.admin.dto;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import lombok.*;

public class ItemsRequestDto {

    private ItemsRequestDto(){};

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class ItemsUpdateInfo{
        private long id;
        private String itemName;
        private int price;
        private String itemDetail;
        private int itemStatus;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemsRegisterInfo{
        private String itemName;
        private int price;
        private String itemDetail;

        public static ItemsRegisterInfo entityToDto(Items items) {
            return ItemsRegisterInfo.builder()
                    .itemName(items.getItemName())
                    .price(items.getPrice())
                    .itemDetail(items.getItemDetail())
                    .build();
        }

    }
}
