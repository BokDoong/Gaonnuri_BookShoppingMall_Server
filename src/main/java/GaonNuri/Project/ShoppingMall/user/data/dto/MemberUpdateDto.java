package GaonNuri.Project.ShoppingMall.user.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {

    String password;
    String name;
    String phone;
    String address;

}
