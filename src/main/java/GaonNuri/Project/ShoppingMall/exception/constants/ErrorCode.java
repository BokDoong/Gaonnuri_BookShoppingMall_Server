package GaonNuri.Project.ShoppingMall.exception.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),

    //404 NOT_FOUND 잘못된 리소스 접근
    AUTHORITY_NOT_FOUND(404, "존재하지 않는 권한 정보입니다."),
    ITEM_NOT_FOUND(404, "존재하지 않는 아이템입니다."),
    CARTITEM_NOT_FOUND(404, "존재하지 않는 장바구니 물품입니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다.."),

    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED_EMAIL(409, "이미 가입되어 있는 이메일입니다."),
    ALREADY_SAVED_ITEM(409, "이미 등록되어 있는 상품입니다"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 서버 팀에 연락주세요!"),

    //400+404 TOKEN
    LOGOUT_MEMBER_ERROR(404, "로그아웃된 사용자입니다"),
    INVALID_REFRESH_TOKEN(400, "Refresh Token 이 유효하지 않습니다.");

    private final int status;
    private final String message;
}
