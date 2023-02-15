package GaonNuri.Project.ShoppingMall.admin.dto;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ItemsRequestDto {

    private ItemsRequestDto(){};

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemsUpdateInfo{
        private long id;
        private String itemName;
        private int price;
        private String itemDetail;
        private ItemStatus itemStatus;

        public static ItemsUpdateInfo entityToDto(Items items) {
            return ItemsUpdateInfo.builder()
                    .id(items.getId())
                    .itemName(items.getItemName())
                    .price(items.getPrice())
                    .itemDetail(items.getItemDetail())
                    .itemStatus(items.getItemStatus())
                    .build();
        }

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
