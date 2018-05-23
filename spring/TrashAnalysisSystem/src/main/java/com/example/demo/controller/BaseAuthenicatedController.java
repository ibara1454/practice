package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.domain.User;
import com.example.demo.domain.SimpleLoginUser;

public class BaseAuthenicatedController {
    // @ModelAttribute("_csrf")
    // public String setCsrfToken(HttpServletRequest request) {
    //     DefaultCsrfToken token = (DefaultCsrfToken) request.getAttribute("_csrf");
    //     return token.getToken();
    // }

    // Setup the login user
    protected User currentUser() {
        return ((SimpleLoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            .getUser();
    }
}
