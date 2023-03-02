package GaonNuri.Project.ShoppingMall.jwt;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtErrorResponse {

    private String errorCode;
    private String message;
}
