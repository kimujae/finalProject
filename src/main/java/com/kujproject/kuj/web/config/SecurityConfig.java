package com.kujproject.kuj.web.config;

import com.kujproject.kuj.web.config.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 대해서 Security를 적용하지 않음으로 설정
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // [STEP1] 서버에 인증정보를 저장하지 않기에 csrf를 사용하지 않는다.


        http.csrf(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(authorize -> authorize.requestMatchers("/resources/**", "/signup", "/login").permitAll())

            // [STEP2] form 기반의 로그인에 대해 비 활성화하며 커스텀으로 구성한 필터를 사용한다.
            .formLogin(AbstractHttpConfigurer::disable)

            .httpBasic(AbstractHttpConfigurer::disable)

            // [STEP3] 토큰을 활용하는 경우 모든 요청에 대해 '인가'에 대해서 사용.
            .authorizeHttpRequests((authz) -> authz.anyRequest().permitAll())

            // [STEP4] Spring Security JWT Filter Load
            .addFilterBefore(jwtAuthorizationFilter(), BasicAuthenticationFilter.class)

            // [STEP5] Session 기반의 인증기반을 사용하지 않고 추후 JWT를 이용하여서 인증 예정
            .sessionManagement((sessionManagement) ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

            // [STEP6] 최종 구성한 값을 사용함.
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() { return new BCryptPasswordEncoder(); }


    /**
     * 5. 비밀번호를 암호화하기 위한 BCrypt 인코딩을 통하여 비밀번호에 대한 암호화를 수행합니다.
     *
     * @return BCryptPasswordEncoder
     */
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }
}
