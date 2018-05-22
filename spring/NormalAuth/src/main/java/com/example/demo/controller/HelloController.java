package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path = "hello")
public class HelloController {

    @GetMapping
    public String greeting() {
        return "hello world";
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
