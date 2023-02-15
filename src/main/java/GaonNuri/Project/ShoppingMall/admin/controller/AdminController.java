package GaonNuri.Project.ShoppingMall.admin.controller;

import GaonNuri.Project.ShoppingMall.admin.service.AdminServiceImpl;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.service.inter.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsRegisterInfo;
import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsUpdateInfo;
import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.DetailItemsInfo;
import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.ItemsInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {

    private final ItemService itemService;
    private final AdminServiceImpl adminServiceImpl;

    //상품목록(관리자 페이지 들어가자마자 뜨는 화면)
    @GetMapping()
    public Page<ItemsInfo> getAllItems(@RequestParam("page") Integer page, @RequestParam("size") Integer size){

        return itemService.showItemsOnly(page, size);
    }

    //상품목록 - 상품 선택
    @GetMapping(value = "/detail")
    public DetailItemsInfo getDetailOfItems(@RequestParam long id) {
        return itemService.ItemsDetails(id);
    }

    //상품 등록
    @PostMapping("/register")
    public DetailItemsInfo registerItems(@RequestBody ItemsRegisterInfo dto) {
        adminServiceImpl.registerItemsInfo(dto);

        DetailItemsInfo result = DetailItemsInfo.builder()
                .itemName(dto.getItemName())
                .price(dto.getPrice())
                .itemDetail(dto.getItemDetail())
                .itemStatus(ItemStatus.FOR_SALE)
                .build();

        return result;
    }

    //상품 수정
    @PutMapping(value = "/update")
    public DetailItemsInfo updateItems(@RequestBody ItemsUpdateInfo dto) {
        adminServiceImpl.updateItemsInfo(dto);

        DetailItemsInfo result = DetailItemsInfo.builder()
                .itemName(dto.getItemName())
                .price(dto.getPrice())
                .itemDetail(dto.getItemDetail())
                .itemStatus(dto.getItemStatus())
                .build();

        return result;
    }


    //상품 삭제



    //주문 조회



}
