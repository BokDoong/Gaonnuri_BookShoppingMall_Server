package GaonNuri.Project.ShoppingMall.item.data.dto;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ItemsResponseDto {

    private ItemsResponseDto(){}

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AdminItemsInfo{
        private String itemName;
        private int price;
        private String itemDetail;
        private ItemStatus itemStatus;

        public static AdminItemsInfo of(Items entity){
            return AdminItemsInfo.builder()
                    .itemName(entity.getItemName())
                    .price(entity.getPrice())
                    .itemDetail(entity.getItemDetail())
                    .itemStatus(entity.getItemStatus())
                    .build();
        }
    }


}
