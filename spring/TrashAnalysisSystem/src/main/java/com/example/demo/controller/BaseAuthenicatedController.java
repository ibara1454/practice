package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.domain.User;
import com.example.demo.domain.SimpleLoginUser;

/**
 *  Base controller of any authenicated controller
 *  Provides method to get current login user
 */
public class BaseAuthenicatedController {
    // Setup the login user
    protected User currentUser() {
        SimpleLoginUser loginUser =
            (SimpleLoginUser)SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
        return loginUser.getUser();
    }
}
