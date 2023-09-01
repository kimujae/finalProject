package com.kujproject.kuj.authentication;

import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.user.AuthDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userDao.findByUserId(username);
        UserEntity user = userEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new PrincipalDetails(
                AuthDto.builder()
                        .userName(user.getUserId())
                        .password(user.getPassword())
                        .build(), authorities);
    }
}
