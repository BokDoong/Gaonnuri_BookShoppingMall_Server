package GaonNuri.Project.ShoppingMall.config.security;

import GaonNuri.Project.ShoppingMall.jwt.JwtAccessDeniedHandler;
import GaonNuri.Project.ShoppingMall.jwt.JwtAuthenticationEntryPoint;
import GaonNuri.Project.ShoppingMall.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final StringRedisTemplate redisTemplate;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // rest api, 로그인 폼 화면 disable
                .httpBasic().disable()

                // rest api, CSRF 보안 disable
                .csrf().disable()

                // jwt token 인증, 세션 Stateless
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // exception handling
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //권한 설정
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**", "/api/v1/item/**").permitAll()
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN") //관리자 인증 필요
                .antMatchers("/api/v1/**").authenticated()

                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider, redisTemplate));
    }
}


