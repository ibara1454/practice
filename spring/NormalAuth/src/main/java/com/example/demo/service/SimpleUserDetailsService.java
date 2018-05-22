package com.example.demo.service;

import com.example.demo.model.SimpleLoginUser;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SimpleUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        // emailでデータベースからユーザーエンティティを検索する
        return userRepository.findByEmail(email)
                .map(SimpleLoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
