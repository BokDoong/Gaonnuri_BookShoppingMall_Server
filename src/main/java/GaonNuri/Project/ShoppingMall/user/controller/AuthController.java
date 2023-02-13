package GaonNuri.Project.ShoppingMall.user.controller;

import GaonNuri.Project.ShoppingMall.user.data.dto.MemberRequestDto;
import GaonNuri.Project.ShoppingMall.user.data.dto.MemberResponseDto;
import GaonNuri.Project.ShoppingMall.user.data.dto.TokenDto;
import GaonNuri.Project.ShoppingMall.user.data.dto.TokenRequestDto;
import GaonNuri.Project.ShoppingMall.user.data.validation.JoinValidation;
import GaonNuri.Project.ShoppingMall.user.data.validation.LoginValidation;
import GaonNuri.Project.ShoppingMall.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<MemberResponseDto> signup(@Validated(JoinValidation.class) @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Validated(LoginValidation.class) @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
