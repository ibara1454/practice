package com.example.demo.controller.app;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import com.example.demo.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;

import java.util.Map;

@RestController
@RequestMapping(path = "user")
public class UsersController {
    @GetMapping
    public String greeting(@AuthenticationPrincipal(expression = "user") User user) {
        // log.info("token : {}", csrfToken.getToken());
        // log.info("access user : {}", user.toString());
        return "hello " + user.getEmail();
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
