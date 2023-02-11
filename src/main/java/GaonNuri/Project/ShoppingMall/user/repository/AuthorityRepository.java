package GaonNuri.Project.ShoppingMall.user.repository;

import GaonNuri.Project.ShoppingMall.user.data.entity.Authority;
import GaonNuri.Project.ShoppingMall.user.data.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityEnum> {
    Optional<Authority> findByAuthorityStatus(AuthorityEnum authorityStatus);
}
