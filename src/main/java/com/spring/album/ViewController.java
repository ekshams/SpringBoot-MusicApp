package com.spring.album;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping({"/", "/album-detail/**", "/new-album", "/edit-album/**"})
    public String index(Model model){
        return "index";
    }
}
