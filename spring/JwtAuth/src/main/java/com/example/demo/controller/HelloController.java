package com.example.demo.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "hello")
public class HelloController {

    @GetMapping
    public String greeting(CsrfToken csrfToken) {
        return "hello world" + csrfToken.getToken();
    }

    @GetMapping(path = "{message}")
    public String greeting(@PathVariable(name = "message") String message) {
        return "hello " + message;
    }

    @PostMapping
    public String postGreeting(@RequestParam(name = "message") String message) {
        return "hello " + message;
    }

}
