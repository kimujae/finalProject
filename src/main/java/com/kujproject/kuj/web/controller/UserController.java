package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.dto.user.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpReqDto signUpReqDto) {
        userService.signUp(signUpReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpReqDto);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id) {
        UserRespDto foundUser = userService.findUserById(id);
        return ResponseEntity.ok().body(foundUser);

    }

    @PatchMapping("/user/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable String id,
                                         @Valid @RequestBody UpdateEmailDto updateEmailDto) {

        userService.updateEmail(id, updateEmailDto);
        return ResponseEntity.ok().body(updateEmailDto);

    }

    @PatchMapping("/user/{id}/passwd")
    public ResponseEntity<?> updatePassword(@PathVariable String id,
                                         @Valid @RequestBody UpdatePasswordDto updatePasswordDto) {

        userService.updatePassword(id, updatePasswordDto);
        return ResponseEntity.ok().body("패스워드 업데이트가 성공적으로 완료되었습니다.");
    }

    @PatchMapping("/user/{id}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable String id,
                                           @Valid @RequestBody UpdateProfileDto updateProfileDto) {

        userService.updateUserProfile(id, updateProfileDto);
        return ResponseEntity.ok().body(updateProfileDto);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
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
