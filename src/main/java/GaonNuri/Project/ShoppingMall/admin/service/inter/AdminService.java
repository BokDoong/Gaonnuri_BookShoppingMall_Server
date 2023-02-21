package GaonNuri.Project.ShoppingMall.admin.service.inter;

import GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.DetailItemsInfo;

public interface AdminService {

    DetailItemsInfo updateItemsInfo(ItemsRequestDto.ItemsUpdateInfo dto, MultipartFile image) throws Exception;

    DetailItemsInfo registerItemsInfo(ItemsRequestDto.ItemsRegisterInfo dto, MultipartFile image) throws IOException;

    void deleteItemsInfo(Long id);
}
