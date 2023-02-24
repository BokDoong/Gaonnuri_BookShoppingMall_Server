package GaonNuri.Project.ShoppingMall.cart.controller;

import GaonNuri.Project.ShoppingMall.cart.data.dto.CartRequestDto;
import GaonNuri.Project.ShoppingMall.cart.data.dto.CartResponseDto;
import GaonNuri.Project.ShoppingMall.cart.repository.CartItemRepository;
import GaonNuri.Project.ShoppingMall.cart.service.CartService;
import GaonNuri.Project.ShoppingMall.order.data.dto.CartOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class CartController {

    private final CartService cartService;
    private final CartItemRepository cartItemRepository;

    //장바구니 담기
    @PostMapping("/cart")
    public ResponseEntity<String> cart(@RequestBody CartRequestDto cartRequestDto) {

        try {
            cartService.addCart(cartRequestDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("장바구니에 추가되었습니다.", HttpStatus.OK);
    }

    //장바구니 확인
    @GetMapping("/cart")
    public Page<CartResponseDto> orderHist(@RequestParam("page") Integer page){

        return cartService.getCartList(page);
    }

    //장바구니 물품 수정(개수)
    @PutMapping("/cartItem")
    public CartResponseDto updateCartItem(@RequestParam("cartItemsId") Long cartItemsId,@RequestParam("count") int count) {
        return cartService.updateCartItemCount(cartItemsId, count);
    }

    //장바구니 물품 삭제
    @DeleteMapping("/cartItem")
    public ResponseEntity<String> deleteCartItem(@RequestParam("cartItemId") Long cartItemId){
        cartService.deleteCartItem(cartItemId);

        return new ResponseEntity<>("삭제되었습니다.", HttpStatus.OK);
    }

    //장바구니 물품 주문
    @PostMapping("/cartItem/orders")
    public ResponseEntity orderCartItem(@RequestBody List<CartOrderDto> cartOrderDtoList) {

        if (cartOrderDtoList.size() == 0) {
            return new ResponseEntity<>("주문할 상품을 선택하세요", HttpStatus.BAD_REQUEST);
        }

        cartService.orderCartItem(cartOrderDtoList);

        return new ResponseEntity<>("상품 주문이 완료되었습니다.", HttpStatus.OK);
    }
}
