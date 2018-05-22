package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;

import java.util.Map;

@RestController
@RequestMapping(path = "user")
@Slf4j
public class UsersController {
    @GetMapping
    public String greeting(@AuthenticationPrincipal(expression = "user") User user, CsrfToken csrfToken) {
        // log.info("token : {}", csrfToken.getToken());
        // log.info("access user : {}", user.toString());
        return "hello " + user.getName();
    }

    @GetMapping(path = "echo/{message}")
    public String getEcho(@PathVariable(name = "message") String message) {
        return message.toUpperCase();
    }

    @PostMapping(path = "echo", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String postEcho(@RequestBody Map<String, String> message) {
        return message.toString();
    }
}
