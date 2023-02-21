package GaonNuri.Project.ShoppingMall.order.service.inter;

import GaonNuri.Project.ShoppingMall.order.data.dto.OrderRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static GaonNuri.Project.ShoppingMall.order.data.dto.OrderResponseDto.AdminOrderInfoDto;
import static GaonNuri.Project.ShoppingMall.order.data.dto.OrderResponseDto.OrderHistoryDto;

public interface OrderService {

    Long order(OrderRequestDto orderRequestDto);

    Page<OrderHistoryDto> getOrderList(Pageable pageable);

    Page<AdminOrderInfoDto> getAllOrderList(Pageable pageable);

    void cancelOrder(Long orderId);

    AdminOrderInfoDto updateOrderStatus(long id, int orderStatus) throws Exception;

}
