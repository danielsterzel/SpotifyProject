package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 1. @Bean -> call method marked with it on startup and save the return as a bean
 * of type SecurityFilterChain
 * 2. SecurityFilterChain -> List of filters executed in EACH HTTP request.
 * 3. HttpSecurity -> configurator. What filters and in what order?
 * 4. requestMatchers(...).permitAll -> everyone can enter /ping
 * 5. anyRequest().authenticated() -> in order to enter all other endpoints you
 * need to be authenticated.
 * 6. oauth2Login() -> If request requires authentication and user is not logged in
 * start the OAuth flow2. What does it do?
 * - redirect to Spotify
 * - get code
 * - exchange tokens
 * - save context
 * 7. AuthenticationEntryPoint -> strategy, when a user that has not been logged in, tries to access a resource that
 * requires authorization.
 * 8. In order for the authentication to be remembered - create an HTTP session
 */


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/ping",
                                "/oauth2/**",
                                "/login/oauth2/**",
                                "/error").permitAll()
                        .anyRequest().authenticated())


                .requestCache(cache -> cache
                        .requestCache(new org.springframework.security.web.savedrequest.HttpSessionRequestCache()))
                .oauth2Login(Customizer.withDefaults());
        //                .exceptionHandling(exception -> exception // if user not logged in redirect him to path
//                        .authenticationEntryPoint(
//                                new LoginUrlAuthenticationEntryPoint("/login/oauth2/code/spotify")
//                        ));

        return http.build();
    }
}
