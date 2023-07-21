package com.kujproject.kuj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dummy")
public class DummyController {
    @RequestMapping("/ex")
    public String dummyFunc(Model model){
        return "index";
    }
}
