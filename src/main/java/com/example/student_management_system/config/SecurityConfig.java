        package com.example.student_management_system.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
    IMPORTANT:

    Replace these imports with the ACTUAL package paths
    from your project if they are different.
*/

import com.example.student_management_system.security.JwtAuthenticationFilter;

import com.example.student_management_system.security.CustomUserDetailsService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                .cors(cors -> {})

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // AUTH APIs
                        .requestMatchers("/auth/**")
                        .permitAll()

                        // SWAGGER
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // ADMIN APIs
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")

                        // STUDENT PANEL APIs
                        .requestMatchers("/student/**")
                        .hasAnyRole("STUDENT", "ADMIN")

                        // ANALYTICS APIs
                        .requestMatchers("/api/analytics/**")
                        .hasRole("ADMIN")

                        // =========================
                        // STUDENT CRUD APIs
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/students/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/students/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/students/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/students/**"
                        )
                        .hasAnyRole("ADMIN", "STUDENT")

                        // =========================
                        // COURSE APIs
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/courses/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/courses/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/courses/**"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/courses/**"
                        )
                        .hasAnyRole("ADMIN", "STUDENT")

                        // DEBUG
                        .requestMatchers("/debug")
                        .authenticated()

                        // ALL OTHER APIs
                        .anyRequest()
                        .authenticated()
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authenticationProvider(
                        authenticationProvider()
                )

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(
                userDetailsService
        );

        authProvider.setPasswordEncoder(
                passwordEncoder()
        );

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
