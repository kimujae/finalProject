package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;
    private final PasswordEncoder passwordEncoder;

    //자동 주입(spring 4.3 < ver)
    public UserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/user")
    public ResponseEntity<SignUpReqDto> signUp(@Valid @RequestBody SignUpReqDto signUpReqDto, BindingResult bindingResult) {
        // bindingResult 에러 검출
        
        userService.signUp(signUpReqDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpReqDto);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<UserRespDto> findUserById(@PathVariable String id) {
        Optional<UserRespDto> foundUser = userService.findUserById(id);

        if(foundUser != null) {
            return ResponseEntity.ok().body(foundUser.get());
        }
        else {
            return ResponseEntity.noContent().build();
        }

    }

    @PatchMapping("/user/{id}/email")
    public ResponseEntity<UpdateEmailReqDto> updateEmail(@PathVariable String id,
                                                         @Valid @RequestBody UpdateEmailReqDto updateEmailReqDto, BindingResult bindingResult) {
        // bindingResult 에러 검출

        userService.updateEmail(id, updateEmailReqDto);
        return ResponseEntity.ok().body(updateEmailReqDto);
    }

    @PatchMapping("/user/{id}/passwd")
    public ResponseEntity<UpdatePasswordReqDto> updateEmail(@PathVariable String id,
                                                            @Valid @RequestBody UpdatePasswordReqDto updatePasswordReqDto, BindingResult bindingResult) {
        // bindingResult 에러 검출

        boolean isUpdated = userService.updatePassword(id,updatePasswordReqDto);

        if(isUpdated) {
            return ResponseEntity.ok().body(updatePasswordReqDto);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/user/{id}/profile")
    public ResponseEntity<UpdateProfileReqDto> updateProfile(@PathVariable String id,
                                                             @Valid @RequestBody UpdateProfileReqDto updateProfileReqDto, BindingResult bindingResult) {
        // bindingResult 에러 검출

        userService.updateUserProfile(id, updateProfileReqDto);
        return ResponseEntity.ok().body(updateProfileReqDto);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable String id) {
        boolean isDeleted = userService.deleteUser(id);

        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
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
