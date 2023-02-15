package GaonNuri.Project.ShoppingMall.admin;

import GaonNuri.Project.ShoppingMall.member.data.entity.Authority;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import GaonNuri.Project.ShoppingMall.member.data.enums.AuthorityEnum;
import GaonNuri.Project.ShoppingMall.member.repository.AuthorityRepository;
import GaonNuri.Project.ShoppingMall.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class AdminTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void 관리자_생성() {

        Authority authority = authorityRepository
                .findByAuthorityStatus(AuthorityEnum.ROLE_ADMIN).orElseThrow(()->new RuntimeException("권한 정보가 없습니다."));
        Set<Authority> set = new HashSet<>();
        set.add(authority);

        Member member = Member.builder()
                .email("ctce7226@gmail.com")
                .password(passwordEncoder.encode("lee~2371765"))
                .authorities(set)
                .phone("01049371765")
                .address("울산")
                .build();

        memberRepository.save(member);

    }
}
