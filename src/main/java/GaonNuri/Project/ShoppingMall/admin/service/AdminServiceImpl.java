package GaonNuri.Project.ShoppingMall.admin.service;

import GaonNuri.Project.ShoppingMall.admin.service.inter.AdminService;
import GaonNuri.Project.ShoppingMall.config.s3.S3Uploader;
import GaonNuri.Project.ShoppingMall.exception.CustomException;
import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import GaonNuri.Project.ShoppingMall.item.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsRegisterInfo;
import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsUpdateInfo;
import static GaonNuri.Project.ShoppingMall.exception.constants.ErrorCode.ALREADY_SAVED_ITEM;
import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.DetailItemsInfo;

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
    public DetailItemsInfo updateItemsInfo(ItemsUpdateInfo dto, MultipartFile image) throws Exception {
        Optional<Items> selectedItems = itemsRepository.findById(dto.getId());

        if (selectedItems.isPresent()) {
            Items items = selectedItems.get();

            items.setItemName(dto.getItemName());
            items.setPrice(dto.getPrice());
            items.setItemDetail(dto.getItemDetail());
            items.setStockNumber(dto.getStockNumber());
            if(dto.getItemStatus()==0) {
                items.setItemStatus(ItemStatus.SOLD_OUT);
            }
            else {
                items.setItemStatus(ItemStatus.FOR_SALE);
            }

            if(!image.isEmpty()) {
                String storedFileName = s3Uploader.upload(image, "images");
                items.setImageUrl(storedFileName);
            }

            itemsRepository.save(items);

            DetailItemsInfo result = DetailItemsInfo.entityToDTO(items);
            return result;
        }
        else {
            throw new Exception();
        }
    }

    /**
     * 상품 등록
     */
    @Transactional
    @Override
    public DetailItemsInfo registerItemsInfo(ItemsRegisterInfo dto, MultipartFile image) throws IOException{

        Items items = new Items();
        items.setItemName(dto.getItemName());
        items.setPrice(dto.getPrice());
        items.setItemDetail(dto.getItemDetail());
        items.setStockNumber(dto.getStockNumber());
        items.setItemStatus(ItemStatus.FOR_SALE);

        if(!image.isEmpty()) {
            String storedFileName = s3Uploader.upload(image,"images");
            items.setImageUrl(storedFileName);
        }

        if (itemsRepository.findByItemName(items.getItemName()) == null) {
            itemsRepository.save(items);
        } else {
            throw new CustomException(ALREADY_SAVED_ITEM);
        }

        DetailItemsInfo result = DetailItemsInfo.entityToDTO(items);

        return  result;
    }

    /**
     * 상품 삭제
     */
    @Override
    public void deleteItemsInfo(Long id) {

        Items selectedItems = itemsRepository.getItemsById(id);
        itemsRepository.delete(selectedItems);
    }

}
