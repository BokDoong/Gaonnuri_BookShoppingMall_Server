package GaonNuri.Project.ShoppingMall.member.repository;

import GaonNuri.Project.ShoppingMall.member.data.entity.Authority;
import GaonNuri.Project.ShoppingMall.member.data.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityEnum> {
    Optional<Authority> findByAuthorityStatus(AuthorityEnum authorityStatus);
}
