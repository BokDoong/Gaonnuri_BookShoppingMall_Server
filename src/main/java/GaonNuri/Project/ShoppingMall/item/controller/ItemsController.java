package GaonNuri.Project.ShoppingMall.item.controller;

import GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto;
import GaonNuri.Project.ShoppingMall.item.service.inter.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.ItemsInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/item")
public class ItemsController {

    private final ItemService itemService;

    //상품 목록페이지
    @GetMapping()
    public Page<ItemsInfo> getAllItems(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return itemService.showItemsOnly(page, size);
    }

    //상품 상세 페이지
    @GetMapping(value = "/detail")
    public ItemsResponseDto.DetailItemsInfo getDetailOfItems(@RequestParam long id) {
        return itemService.ItemsDetails(id);
    }
}
