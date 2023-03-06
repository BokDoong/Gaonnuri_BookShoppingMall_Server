package GaonNuri.Project.ShoppingMall.exception;

import GaonNuri.Project.ShoppingMall.exception.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
}
