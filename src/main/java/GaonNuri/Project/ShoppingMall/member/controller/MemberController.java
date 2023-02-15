package GaonNuri.Project.ShoppingMall.member.controller;

import GaonNuri.Project.ShoppingMall.member.data.dto.MemberResponseDto;
import GaonNuri.Project.ShoppingMall.member.data.dto.MemberUpdateDto;
import GaonNuri.Project.ShoppingMall.member.data.validation.UpdateValidation;
import GaonNuri.Project.ShoppingMall.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @PutMapping("/update")
    public ResponseEntity<MemberResponseDto> updateMyInfo(@Validated(UpdateValidation.class) @RequestBody MemberUpdateDto dto) {
        memberService.updateMyInfo(dto);
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        memberService.logout(request);
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}
