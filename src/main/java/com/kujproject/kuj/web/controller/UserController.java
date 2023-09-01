package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.dto.user.*;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@Tag(name = "USER", description = "USER CRUD API입니다.")
public class UserController {
    private final UserService userService;


    @Operation(summary = "User조회", description = "userId로 User를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable String userId) {

        UserRespDto foundUser = userService.findUserById(userId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(foundUser)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "User 정보 업데이트", description = "userId를 가진 User의 이메일을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 이메일 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/user/{userId}/email")
    public ResponseEntity<ApiResponse> updateEmail(@PathVariable String id,
                                         @Valid @RequestBody UpdateEmailDto updateEmailDto) {

        updateEmailDto = userService.updateEmail(id, updateEmailDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateEmailDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "User 정보 업데이트", description = "userId를 가진 User의 비밀번호를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 비밀번호 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/user/{userId}/passwd")
    public ResponseEntity<ApiResponse> updatePassword(@PathVariable String id,
                                         @Valid @RequestBody UpdatePasswordDto updatePasswordDto) {

        userService.updatePassword(id, updatePasswordDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result("비밀번호가 성공적으로 수정되었습니다.")
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "User 정보 업데이트", description = "userId를 가진 User의 프로필을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 프로필 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/user/{userId}/profile")
    public ResponseEntity<ApiResponse> updateProfile(@PathVariable String id,
                                           @Valid @RequestBody UpdateProfileDto updateProfileDto) {

        updateProfileDto = userService.updateUserProfile(id, updateProfileDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateProfileDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "User 삭제", description = "userId를 가진 User를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "유저 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable String id) {

        userService.deleteUser(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }

}
