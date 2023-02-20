package GaonNuri.Project.ShoppingMall.admin.service.inter;

import GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdminService {

    void updateItemsInfo(ItemsRequestDto.ItemsUpdateInfo dto);

    void registerItemsInfo(ItemsRequestDto.ItemsRegisterInfo dto);

    void registerItemsImg(Long id, MultipartFile image) throws IOException;

    void deleteItemsInfo(Long id);
}
