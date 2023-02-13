package GaonNuri.Project.ShoppingMall.user.data.dto;

import GaonNuri.Project.ShoppingMall.user.data.validation.UpdateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {

    @NotEmpty(message = "패스워드 입력은 필수 값입니다.", groups = UpdateValidation.class)
    String password;

    @NotEmpty(message = "이름을 입력해주세요.", groups = UpdateValidation.class)
    String name;

    String phone;
    String address;

}
