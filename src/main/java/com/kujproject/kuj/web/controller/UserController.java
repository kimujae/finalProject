package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.dto.user.*;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import com.kujproject.kuj.web.common.utils.TokenUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {
    private final UserService userService;

    //자동 주입(spring 4.3 < ver)
    public UserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
    }
    
    @PostMapping("/user")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody SignUpReqDto signUpReqDto) {

        UserRespDto userRespDto = userService.signUp(signUpReqDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(userRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @GetMapping("/user")
    public ResponseEntity<ApiResponse> findUserById() {

        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        //System.out.println(id);
        UserRespDto foundUser = userService.findUserById(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(foundUser)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/user/{id}/email")
    public ResponseEntity<ApiResponse> updateEmail(@PathVariable String id,
                                         @Valid @RequestBody UpdateEmailDto updateEmailDto) {

        updateEmailDto = userService.updateEmail(id, updateEmailDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateEmailDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/user/{id}/passwd")
    public ResponseEntity<ApiResponse> updatePassword(@PathVariable String id,
                                         @Valid @RequestBody UpdatePasswordDto updatePasswordDto) {

        userService.updatePassword(id, updatePasswordDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result("비밀번호가 성공적으로 수정되었습니다.")
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/user/{id}/profile")
    public ResponseEntity<ApiResponse> updateProfile(@PathVariable String id,
                                           @Valid @RequestBody UpdateProfileDto updateProfileDto) {

        updateProfileDto = userService.updateUserProfile(id, updateProfileDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateProfileDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable String id) {

        userService.deleteUser(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
