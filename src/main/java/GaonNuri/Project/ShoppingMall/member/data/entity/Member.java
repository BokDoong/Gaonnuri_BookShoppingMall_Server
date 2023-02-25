package GaonNuri.Project.ShoppingMall.member.data.entity;

import GaonNuri.Project.ShoppingMall.member.data.dto.MemberUpdateDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "member_id")
    private long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String address;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name="member_id",referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_status",referencedColumnName = "authority_status")})
    private Set<Authority> authorities = new HashSet<>();

    @Builder
    public Member(String email, String name, String password, String phone, String address, Set<Authority> authorities) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.authorities = authorities;
    }

    // 회원 정보 수정
    public void updateMember(MemberUpdateDto dto, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(dto.getPassword());
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
    }

    //비밀번호 수정
    public void updatePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }
}
