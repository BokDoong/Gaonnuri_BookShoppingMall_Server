package GaonNuri.Project.ShoppingMall.admin.service;

import GaonNuri.Project.ShoppingMall.admin.service.inter.AdminService;
import GaonNuri.Project.ShoppingMall.config.s3.S3Uploader;
import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsRegisterInfo;
import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsUpdateInfo;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ItemsRepository itemsRepository;
    private final S3Uploader s3Uploader;

    /**
     * 상품 수정
     */
    @Transactional
    @Override
    public void updateItemsInfo(ItemsUpdateInfo dto){
        Items items = itemsRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("상품 정보가 없습니다."));

        items.updateItem(dto);
        itemsRepository.save(items);
    }

    /**
     * 상품 등록
     */
    @Transactional
    @Override
    public void registerItemsInfo(ItemsRegisterInfo dto){

        Items items = new Items();
        items.setItemName(dto.getItemName());
        items.setPrice(dto.getPrice());
        items.setItemDetail(dto.getItemDetail());
        items.setItemStatus(ItemStatus.FOR_SALE);

        itemsRepository.save(items);
    }

    @Override
    public void registerItemsImg(Long id, MultipartFile image) throws IOException {

        Items items = itemsRepository.getItemsById(id);

        if(!image.isEmpty()) {
            String storedFileName = s3Uploader.upload(image,"images");
            items.setImageUrl(storedFileName);
        }

        itemsRepository.save(items);
    }

    @Override
    public void deleteItemsInfo(Long id) {

        Items selectedItems = itemsRepository.getItemsById(id);
        itemsRepository.delete(selectedItems);
    }

}
