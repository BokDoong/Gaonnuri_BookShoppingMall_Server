package GaonNuri.Project.ShoppingMall.order.controller;

import GaonNuri.Project.ShoppingMall.order.data.dto.OrderRequestDto;
import GaonNuri.Project.ShoppingMall.order.service.inter.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/do")
    public ResponseEntity<String> orderItems(@RequestBody OrderRequestDto dto) {

        return new ResponseEntity<>("주문을 완료하였습니다.", HttpStatus.OK);
    }

//    @PostMapping("/cancel")
//    public ResponseEntity<String> cancelItems(@RequestParam("orderId") Long orderId) {
//
//        orderService.cancelOrder(orderId);
//        return new ResponseEntity<>("취소가 완료되었습니다.", HttpStatus.OK);
//    }
}
