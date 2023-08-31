package com.kujproject.kuj.authentication;

import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.dto.user.AuthDto;
import com.kujproject.kuj.dto.user.UserRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/*
    시큐리티 설정에서 loginProcessingUrl 설정을 보고 요청이 파싱되면,
    자동으로 UserDetailsService 타입으로 Ioc되어있는 loadUserByUsername 메소드 실행
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthDto userDto = AuthDto
                .builder()
                .userId(username)
                .build();

        // 사용자 정보가 존재하지 않는 경우
        if (username == null || username.equals("")) {
            return userService.login(username)
                    .map(u -> new PrincipalDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
                    .orElseThrow(() -> new AuthenticationServiceException(username));
        }
        // 비밀번호가 맞지 않는 경우
        else {
            return userService.login(username)
                    .map(u -> new PrincipalDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getUserId()))))
                    .orElseThrow(() -> new BadCredentialsException(username));
        }

    }
}
