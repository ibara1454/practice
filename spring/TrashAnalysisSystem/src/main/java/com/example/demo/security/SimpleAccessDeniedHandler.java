package com.example.demo.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.web.csrf.*;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * アクセスするリソースの認可に失敗した時の処理
 */
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(SimpleAccessDeniedHandler.class);
    public SimpleAccessDeniedHandler() {

    }
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException exception) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        } else {
            dump(exception);
            response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
        }
    }

    private void dump(AccessDeniedException e) {
        if (e instanceof AuthorizationServiceException) {
            logger.info("AuthorizationServiceException : {}", e.getMessage());
        } else if (e instanceof MissingCsrfTokenException) {
            logger.info("org.springframework.security.web.csrf.MissingCsrfTokenException : {}", e.getMessage());
        } else if (e instanceof InvalidCsrfTokenException) {
            logger.info("org.springframework.security.web.csrf.InvalidCsrfTokenException : {}", e.getMessage());
        } else if (e instanceof CsrfException) {
            logger.info("org.springframework.security.web.csrf.CsrfException : {}", e.getMessage());
        } else {
            logger.info("AccessDeniedException : {}", e.getMessage());
        }
    }
}
