package GaonNuri.Project.ShoppingMall.member.repository;

import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNameAndEmail(String name, String email);

    boolean existsByEmail(String email);
}