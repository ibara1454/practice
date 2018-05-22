package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 認証が成功したときの処理
 */
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public SimpleAuthenticationSuccessHandler() {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        } else {
            response.setStatus(HttpStatus.OK.value());
            clearAuthenticationAttributes(request);
        }
    }
    /**
     * Removes temporary authentication-related data which may have been stored in the
     * session during the authentication error
     */
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return;
        } else {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
