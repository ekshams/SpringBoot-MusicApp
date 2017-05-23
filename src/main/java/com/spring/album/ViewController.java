package com.spring.album;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("message", "Welcome to Thymeleaf");
        return "index";
    }
}
