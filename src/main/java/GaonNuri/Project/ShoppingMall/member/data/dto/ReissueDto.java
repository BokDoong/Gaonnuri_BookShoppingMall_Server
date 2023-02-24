package GaonNuri.Project.ShoppingMall.member.data.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReissueDto {

    private String accessToken;
    private String refreshToken;
}
