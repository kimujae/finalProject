package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.authentication.AuthProperties;
import com.kujproject.kuj.domain.service.AuthenticationService;
import com.kujproject.kuj.dto.user.AuthDto;
import com.kujproject.kuj.dto.user.SignUpReqDto;
import com.kujproject.kuj.dto.user.SigninRespDto;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import com.kujproject.kuj.web.common.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Authentication", description = "인증 API입니다.")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @Operation(summary = "회원가입", description = "회원가입 기능입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody SignUpReqDto signUpReqDto) {

        UserRespDto userRespDto = authenticationService.signUp(signUpReqDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(userRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }

    @Operation(summary = "로그인", description = "로그인 기능입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> signin(@Valid @RequestBody AuthDto authDto) {

        SigninRespDto signinRespDto = authenticationService.login(authDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(signinRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PostMapping("/generateToken")
    @Operation(summary = "토큰발급", description = "토큰발급 기능입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "토큰발급 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
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
}
