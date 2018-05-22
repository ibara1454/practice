package com.example.demo.service;

import com.example.demo.model.SimpleLoginUser;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public SimpleUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .map(SimpleLoginUser::new)
            .orElseThrow(() -> {
                return new UsernameNotFoundException("user not found");
            });
    }
}
