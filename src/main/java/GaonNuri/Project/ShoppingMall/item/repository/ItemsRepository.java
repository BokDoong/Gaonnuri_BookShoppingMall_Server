package GaonNuri.Project.ShoppingMall.item.repository;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Long> {

    Items getItemsById(long id);

    List<Items> findAll(Sort sort);

    Items findByItemName(String name);


}
