package GaonNuri.Project.ShoppingMall.order.service;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.repository.ItemsRepository;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import GaonNuri.Project.ShoppingMall.member.repository.MemberRepository;
import GaonNuri.Project.ShoppingMall.member.utils.SecurityUtil;
import GaonNuri.Project.ShoppingMall.order.data.dto.OrderRequestDto;
import GaonNuri.Project.ShoppingMall.order.data.entity.Order;
import GaonNuri.Project.ShoppingMall.order.data.entity.OrderItem;
import GaonNuri.Project.ShoppingMall.order.data.enums.OrderStatus;
import GaonNuri.Project.ShoppingMall.order.exception.OutOfStockException;
import GaonNuri.Project.ShoppingMall.order.repository.OrderRepository;
import GaonNuri.Project.ShoppingMall.order.service.inter.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static GaonNuri.Project.ShoppingMall.order.data.dto.OrderResponseDto.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ItemsRepository itemsRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Override
    public Long order(OrderRequestDto orderRequestDto) {

        //토큰에서 member 갖고온다
        Member member = getMember();
        //아이템 정보 들고오기
        Items items = itemsRepository
                .findById(orderRequestDto.getItemId())
                .orElseThrow(() -> new RuntimeException("주문 상품 정보가 없습니다."));

        //SoldOut 주문x
        CheckSoldOut(items);

        //상품 재고체크 + DB에 반영
        CheckStock(orderRequestDto, items);

        //주문 상품(OrderItem)
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItems(items, orderRequestDto.getCount());
        orderItemList.add(orderItem);

        //주문(Order)
        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    private static void CheckSoldOut(Items items) {
        if(items.getItemStatus() == ItemStatus.SOLD_OUT) {
            throw new OutOfStockException("품절된 상품입니다.");
        }
    }

    private void CheckStock(OrderRequestDto orderRequestDto, Items items) {
        int orderCount = orderRequestDto.getCount();
        int restCount = items.getStockNumber() - orderCount;
        if (restCount < 0) {
            throw new OutOfStockException("상품 재고가 부족합니다.(현재 수량: " + items.getStockNumber() + ".");
        } else if (restCount == 0) {
            items.setItemStatus(ItemStatus.SOLD_OUT);
            items.setStockNumber(restCount);
            itemsRepository.save(items);
        }   //솔드아웃
        else {
            items.setStockNumber(restCount);
            itemsRepository.save(items);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Page<OrderHistoryDto> getOrderList(Pageable pageable) {
        //토큰에서 member 갖고온다
        Member member = getMember();

        //찾은 member 의 주문내역+개수 조회
        List<Order> orders = orderRepository.findOrders(member.getEmail(), pageable);
        Long totalCount = orderRepository.countOrder(member.getEmail());

        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);
            List<OrderItem> orderItems = order.getOrderItems();

            for (OrderItem orderItem : orderItems) {
                OrderItemDto orderItemDto = new OrderItemDto(orderItem);
                orderHistoryDto.addOrderItemDto(orderItemDto);
            }

            orderHistoryDtos.add(orderHistoryDto);
        }

        return new PageImpl<OrderHistoryDto>(orderHistoryDtos, pageable, totalCount);
    }

    private Member getMember() {
        return memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdminOrderInfoDto> getAllOrderList(Pageable pageable) {
        //DB 모든 주문 리스트
        List<Order> orders = orderRepository.findAllOrders(pageable);
        Long totalCount = orders.stream().count();

        //반환리스트
        List<AdminOrderInfoDto> adminOrderInfoDtos = new ArrayList<>();

        //하나씩 담기
        for (Order order : orders) {

            String name = order.getMember().getName();
            AdminOrderInfoDto adminOrderInfoDto = new AdminOrderInfoDto(order, name);

            adminOrderInfoDtos.add(adminOrderInfoDto);
        }

        return new PageImpl<AdminOrderInfoDto>(adminOrderInfoDtos, pageable, totalCount);
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.getById(orderId);

        for(OrderItem orderItem : order.getOrderItems()){
            Items items = orderItem.getItems();

            //품절인지 체크
            if (items.getItemStatus() == ItemStatus.SOLD_OUT) {
                items.setItemStatus(ItemStatus.FOR_SALE);
            }

            //재고 증가
            int nowStock = items.getStockNumber();
            items.setStockNumber(nowStock + orderItem.getCount());

            itemsRepository.save(items);
        }

        //주문취소 저장
        order.setOrderStatus(OrderStatus.CANCEL);
        orderRepository.save(order);
    }
}
