package com.kujproject.kuj.controller;

import com.kujproject.kuj.domain.UserVo;
import com.kujproject.kuj.entity.User;
import com.kujproject.kuj.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserController {
    private UserService userService;
    private final PasswordEncoder passwordEncoder;

    //자동 주입(spring 4.3 < ver)
    public UserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/loginform")
    public String loginform(){
        return "users/loginform";
    }

    @GetMapping("/loginerror")
    public String loginerror(@RequestParam("login_error")String loginError){
        return "/users/loginerror";
    }

    @GetMapping("/signinform")
    public String signinform(){
        return "/users/signinform";
    }

    // 사용자가 입력한 name, email, password가 member에 저장된다.
    @PostMapping("/signin")
    public String signin(@ModelAttribute UserVo userVo){
        System.out.println(userVo.getUserId() + " "+ userVo.getUserName() + " "+ userVo.getPassword());
        userVo.setRole("ROLE_USER");

        userService.createUser(userVo);
        return "redirect:/";
    }

    @GetMapping("/userinfo")
    public String userInfo(Principal principal, ModelMap modelMap){
        String loginId = principal.getName();
        User user = userService.searchUserById(loginId).get();
        modelMap.addAttribute("user", user);

        return "/users/userinfo";
    }
}
