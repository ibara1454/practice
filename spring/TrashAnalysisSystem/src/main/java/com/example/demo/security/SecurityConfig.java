package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            // .mvcMatchers()
            //     .permitAll()
            .anyRequest()
                .authenticated()
            .and()
            // EXCEPTION
            // .exceptionHandling()
            //     .authenticationEntryPoint(authenticationEntryPoint())
            //     .accessDeniedHandler(accessDeniedHandler())
            // .and()
            // LOGIN
            .formLogin()
                .loginProcessingUrl("/authenticate").permitAll()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/app/trashes", false)
                    .usernameParameter("email")
                    .passwordParameter("password")
            .and()
            // LOGOUT
            .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true) // Remove login session (?)
                .deleteCookies("JSESSIONID") // Remove cookie
                .logoutSuccessUrl("/login")
            .and()
            // CSRF
            .csrf()
                // .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                // .csrfTokenRepository(new CookieCsrfTokenRepository())
                .disable()
            ;

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                @Qualifier("simpleUserDetailsService") UserDetailsService userDetailsService,
                                PasswordEncoder passwordEncoder) throws Exception {
        auth.eraseCredentials(true)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}
