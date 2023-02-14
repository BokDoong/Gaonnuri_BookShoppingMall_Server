package GaonNuri.Project.ShoppingMall.item.data.dto;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ItemsResponseDto {

    private ItemsResponseDto(){}

    //관리자 페이지에서 보는 상품 정보 목록
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminItemsInfo{

        private String itemName;
        private int price;
        private String itemDetail;
        private ItemStatus itemStatus;

        public static AdminItemsInfo entityToDTO(Items entity){
            return AdminItemsInfo.builder()
                    .itemName(entity.getItemName())
                    .price(entity.getPrice())
                    .itemDetail(entity.getItemDetail())
                    .itemStatus(entity.getItemStatus())
                    .build();
        }
    }

    //상품 누르면 누구나 볼 수 있는 정보
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserItemsInfo {

        private String itemName;
        private int price;
        private ItemStatus itemStatus;

        public static UserItemsInfo entityToDTO(Items entity) {
            return UserItemsInfo.builder()
                    .itemName(entity.getItemName())
                    .price(entity.getPrice())
                    .itemStatus(entity.getItemStatus())
                    .build();
        }

    }
}
