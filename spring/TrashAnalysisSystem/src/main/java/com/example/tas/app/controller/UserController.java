package com.example.tas.app.controller;

import com.example.tas.domain.repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/index";
    }
}
