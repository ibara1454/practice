package com.example.demo.controller;

import com.example.demo.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class AuthenticationController {
    @ModelAttribute
    public User setUpForm() {
        return new User();
    }

    @GetMapping(path = "login")
    public String login(Model model) {
        return "login";
    }
}
