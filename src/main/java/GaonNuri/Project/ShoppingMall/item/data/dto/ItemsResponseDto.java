package GaonNuri.Project.ShoppingMall.item.data.dto;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import lombok.*;

public class ItemsResponseDto {

    private ItemsResponseDto(){}

    //상품 설명 포함
    @Data
    @Builder
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailItemsInfo{

        private Long id;
        private String itemName;
        private int price;
        private String itemDetail;
        private ItemStatus itemStatus;
        private int stockNumber;
        private String imageUrl;

        public static DetailItemsInfo entityToDTO(Items entity){
            return DetailItemsInfo.builder()
                    .id(entity.getId())
                    .itemName(entity.getItemName())
                    .price(entity.getPrice())
                    .itemDetail(entity.getItemDetail())
                    .itemStatus(entity.getItemStatus())
                    .stockNumber(entity.getStockNumber())
                    .imageUrl(entity.getImageUrl())
                    .build();
        }
    }


    //상품 설명 제거
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemsInfo {

        private Long id;
        private String itemName;
        private int price;
        private ItemStatus itemStatus;
        private String imageUrl;

        public static ItemsInfo entityToDTO(Items entity) {
            return ItemsInfo.builder()
                    .id(entity.getId())
                    .itemName(entity.getItemName())
                    .price(entity.getPrice())
                    .itemStatus(entity.getItemStatus())
                    .imageUrl(entity.getImageUrl())
                    .build();
        }

    }
}
