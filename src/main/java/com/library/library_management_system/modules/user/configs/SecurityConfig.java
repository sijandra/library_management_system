package com.library.library_management_system.modules.user.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/lms/user/**", "/api/lms/book/**", "/api/lms/books/**",
                                "/api/lms/borrow/**", "/api/lms/author/**", "/api/lms/genre/**", "/api/lms/section/**",
                                "/api/lms/publisher/**")
                        .permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}