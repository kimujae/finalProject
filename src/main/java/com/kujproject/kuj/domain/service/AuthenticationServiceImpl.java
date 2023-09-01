package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.authentication.PrincipalDetailsService;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.user.AuthDto;
import com.kujproject.kuj.dto.user.SignUpReqDto;
import com.kujproject.kuj.dto.user.SigninRespDto;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.utils.TokenUtils;
import jakarta.annotation.Resource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    @Resource
    private PrincipalDetailsService principalDetailsService;
    private final UserDao userDao;
    @NonNull
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRespDto signUp(SignUpReqDto signUpReqDto) {
        //password 인코딩
        String encodedPassword = passwordEncoder.encode(signUpReqDto.getPassword());
        signUpReqDto.encodingPasswordBy(encodedPassword);

        // SignUpReqDto -> UserEntity 변환
        UserEntity userEntity = UserEntity.convertedBy(signUpReqDto);
        userEntity.setRole("ROLE_USER");
        userDao.save(userEntity);

        //UserEntity -> UserRespDto 변환
        UserRespDto userRespDto = UserRespDto.convertedBy(userEntity).build();
        return userRespDto;
    }

    @Override
    public SigninRespDto login(AuthDto authDto) {
        UserDetails userDetails = principalDetailsService.loadUserByUsername(authDto.getUserName());
        if(! passwordEncoder.matches(authDto.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        return SigninRespDto.builder()
                .userId(userDetails.getUsername())
                .token(TokenUtils.generateJwtToken(authDto))
                .build();
    }
}
