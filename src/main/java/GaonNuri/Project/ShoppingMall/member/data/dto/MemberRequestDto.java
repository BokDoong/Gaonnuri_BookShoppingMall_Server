package GaonNuri.Project.ShoppingMall.member.data.dto;

import GaonNuri.Project.ShoppingMall.member.data.entity.Authority;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import GaonNuri.Project.ShoppingMall.member.data.validation.JoinValidation;
import GaonNuri.Project.ShoppingMall.member.data.validation.LoginValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    @NotEmpty(message = "이메일 입력은 필수 값입니다.", groups = {JoinValidation.class, LoginValidation.class})
    @Email(message = "이메일 형식을 지켜주세요.", groups = {JoinValidation.class, LoginValidation.class})
    private String email;

    @NotEmpty(message = "패스워드 입력은 필수 값입니다.", groups = {JoinValidation.class, LoginValidation.class})
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.", groups = JoinValidation.class)
    private String password;

    @NotEmpty(message = "이름을 입력해주세요.", groups = JoinValidation.class)
    private String name;

    private String phone;
    private String address;

    public Member toMember(PasswordEncoder passwordEncoder, Set<Authority> authorities) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phone(phone)
                .address(address)
                .authorities(authorities)
                .build();
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
