package GaonNuri.Project.ShoppingMall.member.data.dto;

import GaonNuri.Project.ShoppingMall.member.data.entity.Authority;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private String email;
    private String name;
    private String phone;
    private String address;
    private Set<Authority> authorities;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getEmail(), member.getName(),
                member.getPhone(), member.getAddress(), member.getAuthorities());
    }
}
