package GaonNuri.Project.ShoppingMall.item.data.dto;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ItemsResponseDto {

    private ItemsResponseDto(){}

    //상품 설명 포함
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailItemsInfo{

        private String itemName;
        private int price;
        private String itemDetail;
        private ItemStatus itemStatus;

        public static DetailItemsInfo entityToDTO(Items entity){
            return DetailItemsInfo.builder()
                    .itemName(entity.getItemName())
                    .price(entity.getPrice())
                    .itemDetail(entity.getItemDetail())
                    .itemStatus(entity.getItemStatus())
                    .build();
        }
    }


    //상품 설명 제거
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemsInfo {

        private String itemName;
        private int price;
        private ItemStatus itemStatus;

        public static ItemsInfo entityToDTO(Items entity) {
            return ItemsInfo.builder()
                    .itemName(entity.getItemName())
                    .price(entity.getPrice())
                    .itemStatus(entity.getItemStatus())
                    .build();
        }

    }
}
