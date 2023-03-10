package GaonNuri.Project.ShoppingMall.admin.controller;

import GaonNuri.Project.ShoppingMall.admin.service.AdminServiceImpl;
import GaonNuri.Project.ShoppingMall.item.service.inter.ItemService;
import GaonNuri.Project.ShoppingMall.order.service.inter.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsRegisterInfo;
import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsUpdateInfo;
import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.DetailItemsInfo;
import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.ItemsInfo;
import static GaonNuri.Project.ShoppingMall.order.data.dto.OrderResponseDto.AdminOrderInfoDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {

    private final ItemService itemService;
    private final AdminServiceImpl adminServiceImpl;
    private final OrderService orderService;

    //상품목록(관리자 페이지 들어가자마자 뜨는 화면)
    @GetMapping()
    public Page<ItemsInfo> getAllItems(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {

        return itemService.showItemsOnly(page, size);
    }

    //상품목록 - 상품 선택
    @GetMapping(value = "/detail")
    public DetailItemsInfo getDetailOfItems(@RequestParam long id) {
        return itemService.ItemsDetails(id);
    }

    //상품 등록
    @PostMapping("/register")
    public DetailItemsInfo registerItems(@RequestPart(value = "dto") ItemsRegisterInfo dto,
                                         @RequestPart(value = "file") MultipartFile file) {
        try {
            return adminServiceImpl.registerItemsInfo(dto, file);
        } catch (Exception e) {
            return null;
        }
    }

    //상품 정보 수정
    @PutMapping(value = "/update")
    public DetailItemsInfo updateItems(@RequestPart(value = "dto") ItemsUpdateInfo dto,
                                       @RequestPart(value = "file") MultipartFile file) throws Exception {

        DetailItemsInfo detailItemsInfo = adminServiceImpl.updateItemsInfo(dto, file);

        return detailItemsInfo;
    }

    //상품 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteItems(@RequestParam Long id) {

        adminServiceImpl.deleteItemsInfo(id);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }


    //주문 조회
    @GetMapping("/orders")
    public Page<AdminOrderInfoDto> getOrderList(@RequestParam("page") Integer page) {

        Pageable pageable = PageRequest.of(page, 3);
        Page<AdminOrderInfoDto> result = orderService.getAllOrderList(pageable);

        return result;
    }

    //주문상태 변경
    @PutMapping("/orders")
    public AdminOrderInfoDto updateOrderStatus(@RequestParam("orderId") Long orderId, @RequestParam("status") Integer status) throws Exception {

        try {
            return orderService.updateOrderStatus(orderId, status);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
