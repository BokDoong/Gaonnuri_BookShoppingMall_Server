package GaonNuri.Project.ShoppingMall.order.data.dto;

import GaonNuri.Project.ShoppingMall.order.data.entity.Order;
import GaonNuri.Project.ShoppingMall.order.data.entity.OrderItem;
import GaonNuri.Project.ShoppingMall.order.data.enums.OrderStatus;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class OrderResponseDto {

    //주문상품
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemDto{

        public OrderItemDto(OrderItem orderItem){
            this.itemName = orderItem.getItems().getItemName();
            this.count = orderItem.getCount();
            this.itemPrice = orderItem.getOrderPrice();
            this.imageUrl = orderItem.getItems().getImageUrl();
        }

        private String itemName;
        private int count;
        private int itemPrice;
        private String imageUrl;
    }

    //주문내역
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderHistoryDto{

        public OrderHistoryDto(Order order) {
            this.orderId = order.getId();
            this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.orderStatus = order.getOrderStatus();
            this.orderPrice = order.getOrderPrice();
        }

        private Long orderId;
        private String orderDate;
        private OrderStatus orderStatus;
        private int orderPrice;
        private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        public void addOrderItemDto(OrderItemDto orderItemDto) {
            orderItemDtoList.add(orderItemDto);
        }
    }

    //관리자페이지 - 회원 주문내역
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminOrderInfoDto{

        public AdminOrderInfoDto(Order order, String userName){
            this.orderId = order.getId();
            this.userName = userName;
            this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.orderStatus = order.getOrderStatus();
            this.orderPrice = order.getOrderPrice();
        }

        private Long orderId;
        private String userName;
        private OrderStatus orderStatus;
        private int orderPrice;
        private String orderDate;
    }
}
