package GaonNuri.Project.ShoppingMall.order.service.inter;

import GaonNuri.Project.ShoppingMall.order.data.dto.OrderRequestDto;
import GaonNuri.Project.ShoppingMall.order.data.dto.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Long order(OrderRequestDto orderRequestDto);

    Page<OrderResponseDto.OrderHistoryDto> getOrderList(Pageable pageable);

    Page<OrderResponseDto.AdminOrderInfoDto> getAllOrderList(Pageable pageable);

    void cancelOrder(Long orderId);

//    Long cartOrder(List<OrderRequestDto> orderRequestDtoList);

}
