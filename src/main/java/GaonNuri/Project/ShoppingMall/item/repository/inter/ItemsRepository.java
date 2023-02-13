package GaonNuri.Project.ShoppingMall.item.repository.inter;

import GaonNuri.Project.ShoppingMall.item.data.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long>, CustomItemsRepository {

    Items getItemsById(long id);

}
