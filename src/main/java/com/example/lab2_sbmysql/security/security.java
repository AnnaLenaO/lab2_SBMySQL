package com.example.lab2_sbmysql.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class security {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(GET, "/categories").permitAll()
                                .requestMatchers(GET, "/categories/{id}").permitAll()
                                .requestMatchers(POST, "/categories").hasRole("ADMIN")
                                .requestMatchers(GET, "/locations").authenticated()
                                .requestMatchers(GET, "/locations/public/**").permitAll()
                                .requestMatchers(POST, "/locations").authenticated()
                                .requestMatchers(PUT, "locations").authenticated()
                                .requestMatchers(DELETE, "/locations/{id}").authenticated()
                                .requestMatchers(GET, "/api/geocode/{latitude}/{longitude}").permitAll()
                                .anyRequest().denyAll())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
        return http.build();
    }
}
