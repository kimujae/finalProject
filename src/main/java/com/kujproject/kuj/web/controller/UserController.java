package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.user.UserVo;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.dto.SignUpReqDto;
import com.kujproject.kuj.dto.UserRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
//    // 사용자가 입력한 name, email, password가 member에 저장된다.
//    @PostMapping("/signin")
//    public String signin(@ModelAttribute UserVo userVo){
//        System.out.println(userVo.getUserId() + " "+ userVo.getUserName() + " "+ userVo.getPassword());
//        userVo.setRole("ROLE_USER");
//
//        userService.createUser(userVo);
//        return "redirect:/";
//    }
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
