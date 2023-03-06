package GaonNuri.Project.ShoppingMall.member.service;

import GaonNuri.Project.ShoppingMall.exception.CustomException;
import GaonNuri.Project.ShoppingMall.jwt.TokenProvider;
import GaonNuri.Project.ShoppingMall.member.data.dto.*;
import GaonNuri.Project.ShoppingMall.member.data.entity.Authority;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import GaonNuri.Project.ShoppingMall.member.data.entity.RefreshToken;
import GaonNuri.Project.ShoppingMall.member.data.enums.AuthorityEnum;
import GaonNuri.Project.ShoppingMall.member.repository.AuthorityRepository;
import GaonNuri.Project.ShoppingMall.member.repository.MemberRepository;
import GaonNuri.Project.ShoppingMall.member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static GaonNuri.Project.ShoppingMall.exception.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthorityRepository authorityRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * 회원가입
     */
    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        // 이메일 중복 회원 검증
        if (memberRepository.existsByEmail(memberRequestDto.getEmail())) {
            //RuntimeException 이 아님
            throw new CustomException(ALREADY_SAVED_EMAIL);
        }

        //member 에 Authority 추가 + 예외 타입 수정
        Authority authority = authorityRepository
                .findByAuthorityStatus(AuthorityEnum.ROLE_USER).orElseThrow(() -> new CustomException(AUTHORITY_NOT_FOUND));

        Set<Authority> set = new HashSet<>();
        set.add(authority);

        Member member = memberRequestDto.toMember(passwordEncoder, set);
        return MemberResponseDto.of(memberRepository.save(member));
    }
    /**
     * 로그인
     */
    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) {
        // Dto 의 email, password 를 받고 UsernamePasswordAuthenticationToken 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // authenticate 메서드가 실행이 기될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // JWT 토큰 생성 + DTO 생성
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        tokenDto.setId(member.getId());
        tokenDto.setEmail(member.getEmail());
        tokenDto.setAuthority(member.getAuthorities());

        // refreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    /**
     * 재발급
     */
    @Transactional
    public ReissueDto reissue(TokenRequestDto tokenRequestDto) {
        // refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomException(INVALID_REFRESH_TOKEN);
        }


        // access Token 에서 Authentication 객체 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // DB 에서 member_id를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new CustomException(LOGOUT_MEMBER_ERROR));

        // refresh Token 이 다르면
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        ReissueDto reissueDto = new ReissueDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());

        // refreshToken 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return reissueDto;
    }

    /**
     * 이름,이메일 검사
     */
    @Transactional
    public long checkInfo(MemberCheckDto memberCheckDto) {

        //해당 이름, 이메일로 회원정보를 가져온다
        Member member = memberRepository.findByNameAndEmail(memberCheckDto.getName(), memberCheckDto.getEmail())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        return member.getId();
    }
}
