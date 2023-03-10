package GaonNuri.Project.ShoppingMall.member.controller;

import GaonNuri.Project.ShoppingMall.member.data.dto.*;
import GaonNuri.Project.ShoppingMall.member.data.validation.JoinValidation;
import GaonNuri.Project.ShoppingMall.member.data.validation.LoginValidation;
import GaonNuri.Project.ShoppingMall.member.service.AuthService;
import GaonNuri.Project.ShoppingMall.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<MemberResponseDto> signup(@Validated(JoinValidation.class) @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Validated(LoginValidation.class) @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<ReissueDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    @PostMapping("/check")
    public ResponseEntity<Long> checkInfo(@RequestBody MemberCheckDto memberCheckDto) {
        return ResponseEntity.ok(authService.checkInfo(memberCheckDto));
    }

    @PutMapping("/reset")
    public ResponseEntity<MemberResponseDto> reset(@RequestBody PasswordResetDto passwordResetDto) {
        return ResponseEntity.ok(memberService.resetPassword(passwordResetDto.getId(), passwordResetDto.getPassword()));
    }
}
