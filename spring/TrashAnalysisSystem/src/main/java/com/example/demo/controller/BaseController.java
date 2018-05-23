package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class BaseController {
    // @ModelAttribute("_csrf")
    // public String setCsrfToken(HttpServletRequest request) {
    //     DefaultCsrfToken token = (DefaultCsrfToken) request.getAttribute("_csrf");
    //     return token.getToken();
    // }
}
