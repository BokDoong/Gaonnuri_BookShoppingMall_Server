package GaonNuri.Project.ShoppingMall.member.service;

import GaonNuri.Project.ShoppingMall.member.data.dto.MemberResponseDto;
import GaonNuri.Project.ShoppingMall.member.data.dto.MemberUpdateDto;
import GaonNuri.Project.ShoppingMall.member.data.entity.Member;
import GaonNuri.Project.ShoppingMall.member.repository.MemberRepository;
import GaonNuri.Project.ShoppingMall.member.repository.RefreshTokenRepository;
import GaonNuri.Project.ShoppingMall.member.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final StringRedisTemplate redisTemplate;

    /**
     * 내 정보 조회
     */
    @Transactional
    public MemberResponseDto getMyInfo() {

        MemberResponseDto result = memberRepository.findById(SecurityUtil.getLoginMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        return result;
    }

    /**
     * 내 정보 수정 (이메일 수정 불가)
     */
    @Transactional
    public void updateMyInfo(MemberUpdateDto dto) {
        Member member = memberRepository
                .findById(SecurityUtil.getLoginMemberId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        member.updateMember(dto,passwordEncoder);
    }

    /**
     * 로그아웃
     */
    @Transactional
    public void logout(HttpServletRequest request) {

        // accessToken redisTemplate 블랙리스트 추가
        String jwt = request.getHeader("Authorization").substring(7);
        ValueOperations<String, String> logoutValueOperations = redisTemplate.opsForValue();
        logoutValueOperations.set(jwt, jwt); // redis set 명령어

        // refreshToken 삭제
        refreshTokenRepository.deleteByKey(String.valueOf(SecurityUtil.getLoginMemberId()))
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    /**
     * 비밀번호 재설정
     */
    @Transactional
    public MemberResponseDto resetPassword(Long memberId, String password) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 유저 정보가 없습니다."));

        member.updatePassword(password, passwordEncoder);

        return MemberResponseDto.of(member);
    }
}
