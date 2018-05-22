package com.example.demo.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.SimpleLoginUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class SimpleTokenFilter extends GenericFilterBean {
    final private UserRepository userRepository;
    final private Algorithm algorithm;

    public SimpleTokenFilter(UserRepository userRepository, String secretKey) {
        Objects.requireNonNull(secretKey, "secret key must be not null");
        this.userRepository = userRepository;
        try {
            this.algorithm = Algorithm.HMAC256(secretKey);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // Receive the authorization token
    private Optional<String> resolveToken(ServletRequest request) {
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return Optional.empty();
        } else {
            return Optional.of(token.substring(7));
        }
    }

    private DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        Optional<String> token = resolveToken(request);
        token.ifPresent();
    }

    private void authentication(DecodedJWT jwt) {
        Integer userId = Integer.valueOf(jwt.getSubject());
        userRepository.findById(userId).ifPresent(user -> {
            SimpleLoginUser simpleLoginUser = new SimpleLoginUser(user);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(simpleLoginUser, null, simpleLoginUser.getAuthorities()));
        });
    }
}
