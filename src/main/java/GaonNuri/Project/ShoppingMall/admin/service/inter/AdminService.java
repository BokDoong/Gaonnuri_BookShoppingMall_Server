package GaonNuri.Project.ShoppingMall.admin.service.inter;

import GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto;

public interface AdminService {

    void updateItemsInfo(ItemsRequestDto.ItemsUpdateInfo dto);

    void registerItemsInfo(ItemsRequestDto.ItemsRegisterInfo dto);
}
