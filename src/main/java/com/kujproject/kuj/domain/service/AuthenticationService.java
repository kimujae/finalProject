package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.user.AuthDto;
import com.kujproject.kuj.dto.user.SignUpReqDto;
import com.kujproject.kuj.dto.user.SigninRespDto;
import com.kujproject.kuj.dto.user.UserRespDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface AuthenticationService {
    UserRespDto signUp(SignUpReqDto signUpReqDto);
    SigninRespDto login(AuthDto authDto);
}
