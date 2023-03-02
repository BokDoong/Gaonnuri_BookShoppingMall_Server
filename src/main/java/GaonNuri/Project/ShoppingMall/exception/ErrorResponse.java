package GaonNuri.Project.ShoppingMall.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

    private final int errorCode;
    private final String message;
}
