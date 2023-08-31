package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.authentication.AuthProperties;
import com.kujproject.kuj.dto.user.AuthDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import com.kujproject.kuj.web.common.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Authentication", description = "인증 API Document")
public class AuthenticationController {

    @PostMapping("/generateToken")
    @Operation(summary = "데모 오퍼레이션", description = "데모 오퍼레이션입니다.")
    public ResponseEntity<ApiResponse> selectCodeList(@RequestBody AuthDto userDto) {

        String resultToken = TokenUtils.generateJwtToken(userDto);
        log.info("컨트롤러 진입");
        ApiResponse ar = ApiResponse.builder()
                // BEARER {토큰} 형태로 반환
                .result(AuthProperties.TOKEN_TYPE + " " + resultToken)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build();

        return new ResponseEntity<>(ar, HttpStatus.OK);
    }

    //login 구현
}
