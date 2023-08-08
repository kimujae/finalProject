package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.dto.user.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {
    private final UserService userService;

    //자동 주입(spring 4.3 < ver)
    public UserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
    }
    
    @PostMapping("/user")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpReqDto signUpReqDto, BindingResult bindingResult) {
        // bindingResult 에러 검출
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }


        userService.signUp(signUpReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpReqDto);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id) {
        try {
            UserRespDto foundUser = userService.findUserById(id);
            return ResponseEntity.ok().body(foundUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/user/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable String id,
                                         @Valid @RequestBody UpdateEmailDto updateEmailDto, BindingResult bindingResult) {
        // bindingResult 에러 검출
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        try {
            userService.updateEmail(id, updateEmailDto);
            return ResponseEntity.ok().body(updateEmailDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/user/{id}/passwd")
    public ResponseEntity<?> updatePassword(@PathVariable String id,
                                         @Valid @RequestBody UpdatePasswordDto updatePasswordDto, BindingResult bindingResult) {
        // bindingResult 에러 검출
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        try {
            userService.updatePassword(id, updatePasswordDto);
            return ResponseEntity.ok().body("패스워드 업데이트가 성공적으로 완료되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/user/{id}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable String id,
                                           @Valid @RequestBody UpdateProfileDto updateProfileDto, BindingResult bindingResult) {
        // bindingResult 에러 검출
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        try {
            userService.updateUserProfile(id, updateProfileDto);
            return ResponseEntity.ok().body(updateProfileDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    @GetMapping("/loginform")
//    public String loginform(){
//        return "users/loginform";
//    }
//
//    @GetMapping("/loginerror")
//    public String loginerror(@RequestParam("login_error")String loginError){
//        return "/users/loginerror";
//    }
//
//    @GetMapping("/signinform")
//    public String signinform(){
//        return "/users/signinform";
//    }
//
//
//    @GetMapping("/userinfo")
//    public String userInfo(Principal principal, ModelMap modelMap){
//        String loginId = principal.getName();
//        UserEntity userEntity = userService.searchUserById(loginId).get();
//        modelMap.addAttribute("user", userEntity);
//
//        return "/users/userinfo";
//    }
}
