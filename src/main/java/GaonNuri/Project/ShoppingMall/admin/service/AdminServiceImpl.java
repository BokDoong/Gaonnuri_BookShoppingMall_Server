package GaonNuri.Project.ShoppingMall.admin.service;

import GaonNuri.Project.ShoppingMall.admin.service.inter.AdminService;
import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsRegisterInfo;
import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsUpdateInfo;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ItemsRepository itemsRepository;

    /**
     * 상품 수정
     */
    @Transactional
    @Override
    public void updateItemsInfo(ItemsUpdateInfo dto){
        Items items = itemsRepository.getItemsById(dto.getId());

        items.updateItem(dto);
    }

    /**
     * 상품 등록
     */
    @Transactional
    @Override
    public void registerItemsInfo(ItemsRegisterInfo dto){

        Items items = Items.builder()
                .itemName(dto.getItemName())
                .price(dto.getPrice())
                .itemDetail(dto.getItemDetail())
                .itemStatus(ItemStatus.FOR_SALE)
                .build();

        itemsRepository.save(items);
    }

    @Override
    public void deleteItemsInfo(Long id) {

        Items selectedItems = itemsRepository.getItemsById(id);
        itemsRepository.delete(selectedItems);
    }

}
