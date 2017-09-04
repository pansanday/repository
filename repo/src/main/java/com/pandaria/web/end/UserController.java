package com.pandaria.web.end;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        return "success";
    }

}