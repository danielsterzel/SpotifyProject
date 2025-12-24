package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 1. @Bean -> call method marked with it on startup and save the return as a bean
 *     of type SecurityFilterChain
 * 2. SecurityFilterChain -> List of filters executed in EACH HTTP request.
 * 3. HttpSecurity -> configurator. What filters and in what order?
 * 4. requestMatchers(...).permitAll -> everyone can enter /ping
 * 5. anyRequest().authenticated() -> in order to enter all other endpoints you
 *     need to be authenticated.
 * 6. oauth2Login() -> If request requires authentication and user is not logged in
 *     start the OAuth flow2. What does it do?
 *          - redirect to Spotify
 *          - get code
 *          - exchange tokens
 *          - save context
 * */


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/ping").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
}
