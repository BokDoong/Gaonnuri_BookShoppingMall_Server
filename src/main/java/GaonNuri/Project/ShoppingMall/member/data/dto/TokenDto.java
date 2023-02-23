package GaonNuri.Project.ShoppingMall.member.data.dto;

import GaonNuri.Project.ShoppingMall.member.data.entity.Authority;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String accessToken;
    private String refreshToken;
    private Long id;
    private String email;
    private Set<Authority> authority;
}
