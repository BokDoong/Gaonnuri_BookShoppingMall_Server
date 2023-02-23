package GaonNuri.Project.ShoppingMall.admin.dto;

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
        private int stockNumber;
        private int itemStatus;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ItemsRegisterInfo{
        private String itemName;
        private int price;
        private int stockNumber;
        private String itemDetail;
    }
}
