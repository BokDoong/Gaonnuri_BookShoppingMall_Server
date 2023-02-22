package GaonNuri.Project.ShoppingMall.item.service.inter;

import GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto;
import org.springframework.data.domain.Page;

public interface ItemService {

    Page<ItemsResponseDto.ItemsInfo> showItemsOnly(int page, int size);

    ItemsResponseDto.DetailItemsInfo ItemsDetails(long id);

}
