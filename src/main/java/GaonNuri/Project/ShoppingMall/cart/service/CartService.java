package GaonNuri.Project.ShoppingMall.cart.service;

import GaonNuri.Project.ShoppingMall.cart.data.dto.CartRequestDto;
import GaonNuri.Project.ShoppingMall.cart.data.dto.CartResponseDto;
import GaonNuri.Project.ShoppingMall.cart.data.entity.Cart;
import GaonNuri.Project.ShoppingMall.cart.data.entity.CartItem;
import GaonNuri.Project.ShoppingMall.cart.repository.CartItemRepository;
import GaonNuri.Project.ShoppingMall.cart.repository.CartRepository;
import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.repository.ItemsRepository;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import GaonNuri.Project.ShoppingMall.member.repository.MemberRepository;
import GaonNuri.Project.ShoppingMall.member.utils.SecurityUtil;
import GaonNuri.Project.ShoppingMall.order.data.dto.CartOrderDto;
import GaonNuri.Project.ShoppingMall.order.data.dto.OrderRequestDto;
import GaonNuri.Project.ShoppingMall.order.exception.OutOfStockException;
import GaonNuri.Project.ShoppingMall.order.service.inter.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ItemsRepository itemsRepository;
    private final OrderService orderService;

    /**
     * 장바구니 담기
     */
    public Long addCart(CartRequestDto cartRequestDto) {

        //유저정보
        Member member = getMember();

        //유저의 장바구니
        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        //원래 담아뒀던 물품인지 조회
        Items items = itemsRepository.findById(cartRequestDto.getItemId()).orElseThrow(() -> new RuntimeException("상품 정보가 없습니다."));
        //SoldOut 인지
        if (items.getItemStatus() == ItemStatus.SOLD_OUT) {
            throw new OutOfStockException("품절된 상품입니다.");
        }

        CartItem cartItem = cartItemRepository.findByCartIdAndItemsId(cart.getId(), items.getId());

        //장바구니에 없던 물품 - 추가
        if (cartItem == null) {
            cartItem = CartItem.creatCartItem(cart, items, cartRequestDto.getCount());
            cartItemRepository.save(cartItem);
        } //이미 존재 - 개수 늘린다
        else {
            cartItem.addCount(cartRequestDto.getCount());
        }

        return cartItem.getId();
    }

    /**
     * 장바구니 조회
     */
    @Transactional(readOnly = true)
    public Page<CartResponseDto> getCartList(int page) {

        List<CartResponseDto> cartResponseDtoList;

        //장바구니
        Member member = getMember();
        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null){
            return null;
        }   //비어있는 경우 - null

        //리스트 -> 페이지
        cartResponseDtoList = cartItemRepository.findCartResponseDtoList(cart.getId());
        PageRequest pageRequest = PageRequest.of(page, 10);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), cartResponseDtoList.size());

        Page<CartResponseDto> cartResponseDtos = new PageImpl<>(cartResponseDtoList.subList(start, end), pageRequest, cartResponseDtoList.size());

        return cartResponseDtos;
    }

    private Member getMember() {
        return memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    /**
     * 장바구니 수정
     */
    public CartResponseDto updateCartItemCount(Long cartItemId, int count) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("장바구니 물품 정보가 없습니다."));

        cartItem.updateCount(count);

        CartResponseDto result = new CartResponseDto(cartItem.getId(), cartItem.getItems().getItemName(), cartItem.getItems().getPrice(), cartItem.getCount(), cartItem.getItems().getImageUrl());
        return result;
    }

    /**
     * 장바구니 물품 삭제
     */
    public void deleteCartItem(Long cartItemId){
        cartItemRepository.deleteById(cartItemId);
    }

    /**
     * 장바구니 상품 주문
     */
    public void orderCartItem(List<CartOrderDto> cartOrderDtoList) {

        List<OrderRequestDto> orderRequestDtoList = new ArrayList<>();

        //cartItem -> OrderRequestDto 후 주문
        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId()).orElseThrow(() -> new RuntimeException("장바구니 물품 정보가 없습니다."));
            OrderRequestDto orderRequestDto = new OrderRequestDto();
            orderRequestDto.setItemId(cartItem.getItems().getId());
            orderRequestDto.setCount(cartItem.getCount());

            orderService.order(orderRequestDto);
        }

        //주문 끝난 장바구니 상품제거
        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId()).orElseThrow(() -> new RuntimeException("장바구니 물품이 없습니다."));
            cartItemRepository.delete(cartItem);
        }
    }
}
